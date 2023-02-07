import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import setupPermission from './permission'


setupPermission(router);

createApp(App)
.use(router)
.use(ElementPlus)
.mount('#app')
