import { createRouter, createWebHashHistory, createWebHistory, RouteRecordRaw } from "vue-router";
import Home from "../views/Home.vue";
import AppA from "../components/constantChildApp/appA/appA.vue";
import AppASub from "../components/constantChildApp/appA/appA-sub.vue";
import { App } from "vue";
import layout from "../layout";





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
        path: "/layout",
        component: layout,
        children: [
            {
                name: "home",
                path: "home",
                component: Home,
            },
        ]
    },
    // {
    //     name: "appA",
    //     path: "/appA",
    //     component: AppA,
    // },
    // {
    //     name: "appA-sub",
    //     path: "/appA/:path",
    //     component: AppASub,
    // }


];
const router = createRouter({
    history: createWebHistory(),
    // base: basename,
    routes,
});




export default router;