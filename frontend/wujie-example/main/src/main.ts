import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import WujieVue from "wujie-vue3";
import EnumServer from './constant/serverEnum';
import router from './router';
import { lifecycles } from './lifecycles';
import "./setupGlobalEvent";










const { bus, setupApp, preloadApp, destroyApp } = WujieVue;





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
    name: "vue3",
    url: "//localhost:7300/",
    exec: true,
    alive: true,
    plugins: [{ cssExcludes: ["https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"] }],
    props,
    // 引入了的第三方样式不需要添加credentials
    fetch: (url, options) =>
        url.includes("//localhost:7300/") ? credentialsFetch(url, options) : window.fetch(url, options),
    degrade,
    ...lifecycles,
});

setupApp({
    name: "vue3-vite",
    exec: true,
    alive: true,
    plugins: [{ cssExcludes: ["https://stackpath.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"] }],
    props,
    degrade,
    ...lifecycles,
});

// 我的 rbac
setupApp({
    name: EnumServer.nameSystemAuth,
    url: EnumServer.urlSystemAuth,
    exec: true,
    alive: true,
    degrade,
    ...lifecycles,
})

// 接入 jsp
setupApp({
    name: EnumServer.nameJspUrl,
    url: EnumServer.jspUrl,
    exec: true,
    alive: true,
    degrade,
    ...lifecycles,
})

bus.$on("click", (msg) => window.alert(msg))

const app = createApp(App)
app
    .use(router)
    .use(WujieVue)
    .mount('#app');

// 挂到全局方便调试
window.app = app;
console.log("main.ts 运行完毕！");
