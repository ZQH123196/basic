import { createRouter, createWebHashHistory, createWebHistory, RouteRecordRaw } from "vue-router";
import Home from "../components/Home.vue";
import Page from "../components/Page.vue";
import NotFound from "../components/NotFound.vue";



// const basename = process.env.NODE_ENV === "production" ? "/demo-vite/" : "";


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
        path: "/page",
        name: "page",
        component: Page,
    },
    { path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound },
];
const router = createRouter({
    history: createWebHistory(),
    // history: createWebHashHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    console.log(`[${from.fullPath}] -> [${to.fullPath}]`);
    next()
})


export {
    routes
}

export default router;