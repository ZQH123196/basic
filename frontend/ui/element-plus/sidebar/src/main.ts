import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import recursionTree from './layout/components/recursionTree'

import * as ElementPlusIconsVue from '@element-plus/icons-vue'




const app = createApp(App)
    .use(router)
    .use(ElementPlus)
    // @ts-ignore
    .use(recursionTree)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.mount('#app')


