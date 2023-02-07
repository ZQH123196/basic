import { App } from "vue";
import { Router, RouteRecordName, RouteRecordRaw } from "vue-router";
import { cacheOptions, plugin, preloadApp, setupApp } from "wujie";
import childAppVue from "../components/childApp/childApp.vue";
import childAppSubVue from "../components/childApp/childAppSub.vue";
import router from "../router";
import { sideBarMenuList } from "../share";
import { initAppA, initAppB, routeModeHash, routeModeHistory } from "./constant";




function generateSetupApp({ wujieProps }: childAppOpsType) {
    setupApp(wujieProps)
    // preload 让子应用在 main.js 中自己注册路由
    preloadApp({
        name: wujieProps.name,
        // TODO git url 这一块并不是必须的，在 example 里面只要传入 name 就行，但是这里会导致 typescript 报错
        url: wujieProps.url
    })
}


/**
 *  每个子应用生成两个路由，
 * 一个是子应用的根路由 /root 使用 childApp.vue，
 * 一个是子应用的 /root/:path 使用 childApp-sub.vue。
 * @param param0 
 * @returns 
 */
function generateRoutes({ routeProps, wujieProps }: childAppOpsType): RouteRecordRaw[] {
    const { name, path, meta: rawMeta } = routeProps
    const meta = {
        wujieProps,
        ...rawMeta,
    }
    let rootRoute: RouteRecordRaw = {
        name,
        path,
        component: childAppVue,
        meta
    }
    let rootSubRoute: RouteRecordRaw = {
        name: name + "-sub",
        path: path + "/:path",
        component: childAppSubVue,
        meta
    }
    return [
        rootRoute,
        rootSubRoute
    ];
}


function registerRoute(router: Router, routes: RouteRecordRaw[]) {
    routes.forEach(route => router.addRoute(route))
}



function credentialsFetch(url, options) {
    return window.fetch(url, { ...options, credentials: "omit" });
}

const degrade = window.localStorage.getItem("degrade") === "true" || !window.Proxy || !window.CustomElementRegistry;



/**
 * 只是注册 menulist 而已，对于主应用而言子应用的路由就是固定的两个
 * 这样子应用可以吧自己需要展示的 menu 放上去，权限也可以控制了
 * 主应用只是展示跟跳转
 * @param childMenulist 
 */
function registerMenu({ appName, menuList: childMenulist }: {
    appName: string;
    menuList: {
        to: string;
        menuText: RouteRecordName | undefined;
    }[];
}) {
    // 通过 name 拿到路由中的根路径
    const rootPath = router.getRoutes().find(route => route.name === appName).path
    // 将根路径拼接到子应用上送的 to 的前缀中
    const _menuList = childMenulist.map(item => { return { ...item, to: rootPath + item.to } })
    sideBarMenuList.push(..._menuList);
}

/**
 * 注册子应用的 vue 实例，并设置其路由的访问模式
 * @param ops 
 */
function registerVueInstance(ops: {
    appName: string;
    vueProps: {
        instance: App<Element>;
    };
}) {
    const childApp = childAppList.find(item => ops.appName == item.wujieProps.name)
    if (!childApp) throw new Error(`找不到此子应用 ${ops.appName} 的注册信息！`);
    if (!ops.vueProps.instance.config.globalProperties.$router) throw new Error(`子应用的实例上没有 $router 对象！考虑让子应用使用 vue router！`);
    childApp.vueProps = ops.vueProps


    // 添加到对应 router 的 meta 中，方法是直接替换路由
    const childRootRoute = router.getRoutes().find(route => route.name === childApp.routeProps.name)
    router.removeRoute(childRootRoute.name)
    childRootRoute.meta.vueProps = ops.vueProps
    childRootRoute.meta.routeMode = getRouterMode(ops.vueProps.instance)
    router.addRoute(childRootRoute)

    const childAppSubRoute = router.getRoutes().find(route => route.name === childApp.routeProps.name + "-sub")
    router.removeRoute(childAppSubRoute.name)
    childAppSubRoute.meta.vueProps = ops.vueProps
    childRootRoute.meta.routeMode = getRouterMode(ops.vueProps.instance)
    router.addRoute(childAppSubRoute)

}

function getRouterMode(instance: App<Element>): "history" | "hash" {
    const base = instance.config.globalProperties.$router.options.history.base
    return base.endsWith("#") ? routeModeHash : routeModeHistory 
}



function registerApp(ops: childAppOpsType) {
    if (ops.routeProps) {
        const routes = generateRoutes(ops);
        registerRoute(router, routes);
    }
    generateSetupApp(ops);
}

// name 应当一致
interface childAppOpsType {
    vueProps?: {
        instance: App<Element>
    },
    menuProps?: {
        text: string,
    },
    routeProps?: {
        name: string,
        path: string,
        meta: {
            routeMode: "history" | "hash"
        }
    },
    wujieProps: cacheOptions,
}



const childAppList = [
    initAppA,
    initAppB
]


childAppList.forEach(childApp => registerApp(childApp))

export {
    childAppList
}

export type {
    childAppOpsType
}
