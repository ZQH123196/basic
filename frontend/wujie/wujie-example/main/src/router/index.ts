import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import Home from "../views/Home.vue";
import Vue3 from "../views/Vue3.vue";
import Vue3Sub from "../views/Vue3-sub.vue";
import Vue3Vite from "../views/Vue3-vite.vue";
import SystemAuth from "../views/System-auth.vue";
import Jsp from "../views/Jsp.vue";


const routes = [
    {
        path: "/home",
        name: "home",
        component: Home,
    },
    {
        path: "/vue3",
        name: "vue3",
        component: Vue3,
    },
    {
        path: "/vue3-sub/:path",
        name: "vue3-sub",
        component: Vue3Sub,
    },
    {
        path: "/vue3-vite",
        name: "vue3-vite",
        component: Vue3Vite,
    },
    {
        path: "/system-auth",
        name: "system-auth",
        component: SystemAuth,
    },
    {
        path: "/jsp",
        name: "jsp",
        component: Jsp,
    },
    {
        path: "/",
        redirect: "/home",
    },
];
const router = createRouter({
    history: createWebHashHistory(),
    // base: basename,
    routes,
});




export default router;