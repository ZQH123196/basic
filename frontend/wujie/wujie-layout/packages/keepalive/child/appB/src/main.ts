import { createApp } from 'vue'
import { createRouter, createWebHistory, RouteRecordNormalized } from 'vue-router';
import App from './App.vue'
import router, { routes } from './router'


const vueApp = createApp(App);

vueApp
    .use(router)
    .mount('#app')
console.log(vueApp);


declare global {
    interface Window {
        // 是否存在无界
        __POWERED_BY_WUJIE__?: boolean;
        // 子应用公共加载路径
        __WUJIE_PUBLIC_PATH__: string;
        // 原生的querySelector
        __WUJIE_RAW_DOCUMENT_QUERY_SELECTOR__: typeof Document.prototype.querySelector;
        // 原生的querySelectorAll
        __WUJIE_RAW_DOCUMENT_QUERY_SELECTOR_ALL__: typeof Document.prototype.querySelectorAll;
        // 原生的window对象
        __WUJIE_RAW_WINDOW__: Window;
        // 子应用沙盒实例
        // __WUJIE: WuJie;
        // 子应用mount函数
        __WUJIE_MOUNT: () => void;
        // 子应用unmount函数
        __WUJIE_UNMOUNT: () => void;
        // 注入对象
        $wujie: {
            // bus: EventBus;
            shadowRoot?: ShadowRoot;
            props?: { [key: string]: any };
            location?: Object;
        };
    }
}

if (window.$wujie) {
    const props = window.$wujie?.props;
    const registerVueInstance = props!.registerVueInstance
    const registerMenu = props!.registerMenu

    let args = {
        appName: "appB",
        vueProps: {
            instance: vueApp
        },
    }
    registerVueInstance(args)

    let menuList = mapRoutes2MenuList(router.getRoutes())
    let menuArgs = {
        appName: "appB",
        menuList
    }
    registerMenu(menuArgs)
}


/**
 * 此处交由子应用自己实现，可以根据需求来显隐在 主应用展示的 menu
 * 比如权限设置某个角色只能看到那些页面
 * @param routes 
 */
function mapRoutes2MenuList(routes: RouteRecordNormalized[]) {
    return routes
        .map(route => {
            return {
                to: route.path,
                menuText: route.name
            }
        })
}

