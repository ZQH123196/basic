import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router"

import One from "@/components/child/One.vue"
import Two from "@/components/child/Two.vue"
import Three from "@/components/child/Three.vue"

const constantRoutes: RouteRecordRaw[] = [
    {
        path: "/One",
        name: "One",
        component: One,
        meta: {
            order: 1,
            menuText: "Navigator One",
        }
    },
    {
        path: "/Two",
        component: Two,
        meta: {
            order: 2,
            menuText: "Navigator Two",
        }
    },
    {
        path: "/Three",
        component: Three,
        meta: {
            order: 3,
            menuText: "Navigator Three",
        }
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes: constantRoutes
})



export default router