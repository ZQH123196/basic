import { createApp } from 'vue'
import App from './App.vue'
import './index.css';
import router from "./router/index"
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'


const app = createApp(App)

app.use(ElementPlus)
app.use(router)


const rootInstance = app.mount('#app')
