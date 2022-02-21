import { createRouter, createWebHashHistory, Router, RouteRecord, RouteRecordRaw, RouterOptions } from 'vue-router';
import notFound from '@/views/notFound/notFound.vue';
import layout from '@/views/layout/index.vue';
import home from '@/views/home/home.vue';

const routes: RouteRecordRaw[] = [
    {
        path: "/",
        redirect: "/home"
    },
    {
        path: "/home",
        name: "home",
        component: layout,
        children: [
            {
                path: "home",
                name: "home",
                component: home
            }
        ]
    },
    {
        path: "/:pathMatch(.*)*",
        name: "notFound",
        component: notFound
    }
]

const routerOpt: RouterOptions = {
    history: createWebHashHistory(),
    routes: routes
}

const router: Router = createRouter(routerOpt);


export default router