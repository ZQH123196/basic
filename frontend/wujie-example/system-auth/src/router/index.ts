import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import HelloWorldVue from "../components/HelloWorld.vue";


const routes = [
    {
        path: "/home",
        name: "home",
        component: HelloWorldVue,
    },
    {
        path: "/childRoute",
        name: "childRoute",
        component: HelloWorldVue,
    },
    {
        path: "/HelloWorldVue",
        name: "HelloWorldVue",
        component: HelloWorldVue,
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