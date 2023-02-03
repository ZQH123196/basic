import { createRouter, createWebHashHistory, createWebHistory, RouteRecordRaw } from "vue-router";
import Home from "../views/Home.vue";
import SystemAuth from "../views/System-auth.vue";
import AppA from "../components/constantChildApp/appA/appA.vue";
import AppASub from "../components/constantChildApp/appA/appA-sub.vue";

import Vite from "../components/constantChildApp/vite/Vite.vue";
import ViteSub from "../components/constantChildApp/vite/Vite-sub.vue";

declare module 'vue-router' {
    interface RouteMeta {
        // 是可选的
        menuProps?: {
            text: string
        }
        // 每个路由都必须声明
        wujieProps?: any
    }
}


const routes: RouteRecordRaw[] = [
    {
        path: "/",
        redirect: "/home",
    },
    {
        name: "home",
        path: "/home",
        component: Home,
    },
    {
        name: "appA",
        path: "/appA",
        component: AppA,
    },
    {
        name: "appA-sub",
        path: "/appA/:path",
        component: AppASub,
    },
    {
        path: "/vite",
        name: "vite",
        component: Vite,
    },
    {
        path: "/vite-sub/:path",
        name: "vite-sub",
        component: ViteSub,
    },


];
const router = createRouter({
    history: createWebHistory(),
    // base: basename,
    routes,
});




export default router;