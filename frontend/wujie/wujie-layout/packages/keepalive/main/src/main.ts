import { createApp } from 'vue'
import App from './App.vue'
import router from './router';
import WujieVue from 'wujie-vue3';


const vueApp = createApp(App)





vueApp
    .use(router)
    
    .mount('#app');


// 引入微前端
import { setupMicroFrameApp } from './microFrame';
setupMicroFrameApp(vueApp)



window.app = vueApp;
export {
    vueApp
}