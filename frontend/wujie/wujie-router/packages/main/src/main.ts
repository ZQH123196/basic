import { createApp } from 'vue'
import App from './App.vue'
import EnumServer from './constant/serverEnum';
import router from './router';
import { lifecycles } from './lifecycles';
import "./setupGlobalEvent";

import WujieVue from "wujie-vue3";
import { createRouter, createWebHistory } from 'vue-router';
const { bus, setupApp, preloadApp, destroyApp } = WujieVue;


const vueApp = createApp(App)
const props = {
    jump: (name) => {
        router.push({ name });
    },
};
function credentialsFetch(url, options) {
    return window.fetch(url, { ...options, credentials: "omit" });
}

const degrade = window.localStorage.getItem("degrade") === "true" || !window.Proxy || !window.CustomElementRegistry;


setupApp({
    name: "appA",
    url: "//localhost:9000/",
    exec: true,
    props,
    fetch: credentialsFetch,
    degrade,
    ...lifecycles,
});

setupApp({
    name: "vite",
    url: "//localhost:7500/",
    exec: true,
    props,
    fetch: credentialsFetch,
    degrade,
    ...lifecycles,
});



vueApp
    .use(router)
    .use(WujieVue)
    .mount('#app');

// 挂到全局方便调试
window.app = vueApp;
console.log("main.ts 运行完毕！");


export {
    vueApp
}