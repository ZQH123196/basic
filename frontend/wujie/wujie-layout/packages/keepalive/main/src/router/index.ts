import { createRouter, createWebHashHistory, createWebHistory, RouteRecordRaw } from "vue-router";
import Home from "../views/Home.vue";
import AppA from "../components/constantChildApp/appA/appA.vue";
import AppASub from "../components/constantChildApp/appA/appA-sub.vue";
import layout from "../layout";





const routes: RouteRecordRaw[] = [
    {
        path: "/",
        redirect: "/layout/home",
    },
    {
        name: "home",
        path: "/home",
        components: { wholePage: Home },
    },
    {
        path: "/layout",
        components: { wholePage: layout },
        children: [
            {
                path: "home",
                components: { appMain: Home },
            },
        ]
    },
    // 下面的已被废弃，交由 constant 注册
    // {
    //     path: "/AppA",
    //     components: {
    //         wholePage: layout,
    //     },
    //     children: [
    //         {
    //             path: "",
    //             components: { appMain: AppA },
    //         },
    //     ]
    // },
    // {
    //     path: "/appA/:path",
    //     components: { wholePage: layout },
    //     children: [
    //         {
    //             path: "appA-sub",
    //             components: { appMain: AppASub },
    //         },
    //     ]
    // },
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