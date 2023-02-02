import { createRouter, createWebHashHistory, createWebHistory, RouteRecordRaw } from "vue-router";
import Home from "../views/Home.vue";
import Vue3 from "../views/Vue3.vue";
import Vue3Sub from "../views/Vue3-sub.vue";
import Vue3Vite from "../views/Vue3-vite.vue";
import SystemAuth from "../views/System-auth.vue";
import Jsp from "../views/Jsp.vue";


const routes = [
    {
        path: "/",
        redirect: "/home",
    },
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

];
const router = createRouter({
    history: createWebHistory(),
    // base: basename,
    routes,
});




export default router;