import Vue, {h} from "vue";
import App from "@/App.vue";
import { vuexStore } from "./store/index";
import router from "./router/index";
import './style.scss'
import VueRouter from "vue-router";

Vue.config.productionTip = false;

Vue.use(VueRouter);
const app = new Vue({
  router,
  // 全局 $store，挂载到实例
  store: vuexStore,
  render: () => h(App),
})

app.$mount("#app");




window.app = app
