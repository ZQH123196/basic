import VueRouter from 'vue-router'

const createRouter = () => new VueRouter({
    routes: [
        {
            path: '/',
            redirect: "/TestLifecycle"
        },
        {
            path: '/TestLifecycle',
            component: () => import("../views/TestLifecycle.vue")
        },
        {
            path: '/TestPinia',
            component: () => import("../views/TestPinia.vue")
        },
        {
            path: '/TestVuexCompositionSetup',
            component: () => import("../views/TestVuexCompositionSetup.vue")
        },
        {
            path: '/TestVuexOptionapi',
            component: () => import("../views/TestVuexOptionapi.vue")
        }
    ]
})

const router = createRouter();
export default router;