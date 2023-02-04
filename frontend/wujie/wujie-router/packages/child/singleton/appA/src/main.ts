import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router';
import App from './App.vue'
import router, { routes } from './router'
import GlobalMixin from "./components/GlobalMixin.vue"


declare global {
    interface Window {
        // 是否存在无界
        __POWERED_BY_WUJIE__?: boolean;
        // 子应用mount函数
        __WUJIE_MOUNT: () => void;
        // 子应用unmount函数
        __WUJIE_UNMOUNT: () => void;
        // 子应用无界实例
        __WUJIE: { mount: () => void };
    }
}

if (window.__POWERED_BY_WUJIE__) {
    let instance: any;
    window.__WUJIE_MOUNT = () => {
        const router = createRouter({ history: createWebHistory(), routes });
        router.beforeEach((to, from, next) => {
            console.log(`[${from.fullPath}] -> [${to.fullPath}]`);
            next()
        })
        instance = createApp(App)
            .mixin(GlobalMixin)
            .use(router);
        instance.mount("#app");
    };
    window.__WUJIE_UNMOUNT = () => {
        instance.unmount();
    };
    // module脚本异步加载，应用主动调用生命周期
    window.__WUJIE.mount();
} else { // 非无界上下文环境，也就是正常开发
    const router = createRouter({ history: createWebHistory(), routes });
    router.beforeEach((to, from, next) => {
        console.log(`[${from.fullPath}] -> [${to.fullPath}]`);
        next()
    })
    createApp(App)
        .mixin(GlobalMixin)
        .use(router)
        .mount('#app')
}

