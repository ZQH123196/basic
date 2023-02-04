import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router';
import App from './App.vue'
import router, { routes } from './router'
import GlobalMixin from "./components/GlobalMixin.vue"


createApp(App)
    .mixin(GlobalMixin)
    .use(router)
    .mount('#app')



