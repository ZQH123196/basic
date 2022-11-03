import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import WujieVue from "wujie-vue3";
import EnumServer from './constant/serverEnum';
import router from './router';
import { lifecycles } from './lifecycles';
import "./setupGlobalEvent";

/**
 * 1、引入 wujievue
 * 2、配置子应用的 name，name 就是 id
 * 3、设置 setupApp，这个也决定了 network 面部中会不会发出请求，其中 url 就是地址，fetch 是需要自定义复杂的 获取请求 url 会作为参数传入
 * 4、编写子组件 vue，<WujieVue width="100%" height="100%" name="vue3-vite" :url="vue3ViteUrl" :sync="true"></WujieVue> 这个 url 应当与 setupApp 中的一致，其实可以不写与 setupApp 中的配置存在一个即可，setupApp 根据 name 来匹配
 * 5、编写 router
 * 
 * 刨除引入就是这样开发
 * 主应用
 * 1、写 setupApp，设置 name、url
 * 2、写 <WujieVue />，匹配 setupApp 的 name，并设置父应用需传递的 props
 * 3、将 <WujieVue /> 的组件引入 router 并分配一个路由地址，<router-link to="" /> 跳转路由
 * 
 * 子应用
 * 1、生命周期改造（可选）
 * 
 * 较好实践：
 * 使用 setupApp 配合 router 的路径来进行匹配，不要把那些与上下文组件无关的配置写在 <WujieVue /> 上
 * 比如 props 写在<WujieVue />，url 写在 setupApp
 * 
 * 生命周期的挂载很简单，无非就是注册到 window 上下文中去
 */






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

bus.$on("click", (msg) => window.alert(msg))

const app = createApp(App)
app
    .use(router)
    .use(WujieVue)
    .mount('#app');

// 挂到全局方便调试
window.app = app;
console.log("main.ts 运行完毕！");
