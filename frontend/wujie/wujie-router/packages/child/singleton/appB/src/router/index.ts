import { createRouter, createWebHashHistory, createWebHistory, RouteRecordRaw } from "vue-router";
import Home from "../components/Home.vue";





const routes = [
    {
        path: "/home",
        name: "home",
        component: Home,
    },
];
const router = createRouter({
    history: createWebHashHistory(),
    routes,
});




export default router;