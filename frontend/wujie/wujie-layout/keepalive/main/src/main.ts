import { createApp } from 'vue'
import App from './App.vue'
import router from './router';
import { lifecycles } from './lifecycles';
import "./setupGlobalEvent";
import WujieVue from 'wujie-vue3';
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// 引入微前端
import "./microFrame"

const vueApp = createApp(App)



vueApp
    .use(router)
    .use(WujieVue)
    .mount('#app');



export {
    vueApp
}