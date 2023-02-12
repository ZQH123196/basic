import { createRouter, createWebHashHistory, Router, RouteRecordRaw } from "vue-router"

import layoutVue from "@/layout/layout.vue"
import One from "@/views/One.vue"
import Two from "@/views/Two.vue"
import Three from "@/views/Three.vue"
import Dashboard from "@/layout/components/dashboard/Dashboard.vue"
import { SideBarVue } from "@/layout/components"


/**
 * 使用 layout 作为顶级组件的原因很简单，只是为了符合 element container 布局而已，这样在实现上就是嵌套路由的布局
 * 但是所有需要符合 container 布局的组件都必须作为 layout 的子组件存在，哪怕 path 不一样顶层也必须使用 layout 作为组件，否则 element container 的布局会被打破
 * 
 * 同时要注意 vueroute 其实会解析 route，将其映射为最小单元
 */
const constantRoutes: RouteRecordRaw[] = [
    {
        path: "/test",
        component: SideBarVue,
    },
    {
        path: "/",
        component: layoutVue,
        children: [
            {
                path: "",
                component: Dashboard,
                meta: {
                    order: 1,
                    menuText: "Dashboard",
                },
            }
        ]
    },
    {
        path: "/pathA",
        component: layoutVue,
        children: [
            {
                path: "One",
                component: One,
                meta: {
                    order: 2,
                    menuText: "Navigator One",
                },
            },
            {
                path: "Two",
                component: Two,
                meta: {
                    order: 3,
                    menuText: "Navigator Two",
                }
            }
        ]
    },
    {
        path: "/pathB",
        component: layoutVue,
        children: [
            {
                path: "Three",
                component: Three,
                meta: {
                    order: 4,
                    menuText: "Navigator Three",
                }
            }
        ]
    },
]

const router: Router = createRouter({
    history: createWebHashHistory(),
    routes: constantRoutes
})




export default router