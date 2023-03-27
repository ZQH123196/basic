import Vue from "vue";
import VueRoute, { RouteRecordPublic } from "vue-router";


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

function setupMicroFramework(vueApp: Vue, router?: VueRoute) {
    const props = window.$wujie?.props;
    const appName = props?.appName;
    const registerVueInstance: Function = props!.registerVueInstance
    const registerMenu: Function = props!.registerMenu

    // vue2 的实例上并没有版本信息，所以要绑定上
    // @ts-ignore
    if (!vueApp.version) {
        // @ts-ignore
        vueApp.version = Vue.version
    }

    let args = {
        appName,
        vueProps: {
            instance: vueApp
        },
    }
    registerVueInstance(args)


    if (router) {
        let menuList = mapRoutes2MenuList(router.getRoutes())
        let menuArgs = {
            appName,
            menuList
        }
        registerMenu(menuArgs)
    }

}



/**
 * 此处交由子应用自己实现，可以根据需求来显隐在 主应用展示的 menu
 * 比如权限设置某个角色只能看到那些页面
 * @param routes 
 */
function mapRoutes2MenuList(routes: RouteRecordPublic[]) {
    return routes
        .map(route => {
            return {
                to: route.path,
                menuText: route.name
            }
        })
}

export { setupMicroFramework }