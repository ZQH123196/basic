import Vue from "vue";
import App from "@/App.vue";
import { vuexStore } from "./store/index";
import router from "./router/index";
import VCA, { createApp, h } from "@vue/composition-api";
import './style.scss'
import VueRouter from "vue-router";
import { createPinia } from 'pinia';

Vue.config.productionTip = false;
const pinia = createPinia()
Vue.use(pinia)
Vue.use(VCA);
Vue.use(VueRouter);
const app = createApp({
  router,
  // 全局 $store，挂载到实例
  store: vuexStore,
  render: () => h(App),
});

app.mount("#app");




window.app = app
