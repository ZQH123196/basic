/**
 * 子应用在 startUp 通知父级自己的目录，并由父级自动构建子应用目录
 * 使用 通讯总线的方式来调用
 * 根据 3、通过 bus 方法发送去中心化的事件 主应用 bus.$on("click", (msg) => window.alert(msg)) 监听子应用的 click 事件 子应用点击按钮 window.$wujie.bus.$emit('click', 'vue3') 发送 click 事件
 * 可以由父应用统一监听 "registerSidebar" 事件，入参为 (routeMap, preFilter, postHandle) -> { // do somthing }
 * routeMap 的格式就是 vue router 的嵌套路由，因此 子应用 可以直接把 vue router 的配置直接丢进去, 
 * preFilter(routeMap) 可选，是用来方便过滤的，比如子应用直接丢 vue router 的配置进去的话可能会附带有 首页 之类的配置，就可以使用这个来过滤，同时也可以作为是否显示这个路径的权限控制(根据父级所传入的 props 中的用户信息来自行判断)
 * postHandle(routerMao) 可选，是用来进行后处理的，这个倒是无所谓，甚至没有也行 
 * 
 */

import { RouteRecordRaw } from "vue-router";
import router from "../router";
import { generateWujieComponent } from "./wujieHelper";





interface IRegisterFilter {
    (routeMap: RouteRecordRaw[]): boolean;
}

interface IRegisterPostHandle {
    (routeMap: RouteRecordRaw[]): void;
}



function registerSidebarFn(routeMap: RouteRecordRaw[]) {
    console.log("into registerSidebarFn!");
    console.log("routeMap", routeMap);
    if (routeMap.forEach) return;
    // 注册实例
    routeMap.forEach(route => {
        let wujieVue = generateWujieComponent(route)

        // vue router 会接管 vue 实例的挂载跟销毁，所以应该直接注册到 vue router 的 router 实例上，也就是 routes 中
        router.addRoute({
            name: route.name,
            path: route.path,
            component: wujieVue
        })
    })


    setTimeout(() => {
        console.log("router.getRoutes()", router.getRoutes());
    }, 3000);
}

function onregisterSidebar(routeMap: any, preFilter?: IRegisterFilter, postHandle?: IRegisterPostHandle): void {
    if (preFilter) preFilter(routeMap);
    registerSidebarFn(routeMap);
    if (postHandle) postHandle(routeMap);
}




export type {
    IRegisterFilter,
    IRegisterPostHandle,
}

export default onregisterSidebar;

