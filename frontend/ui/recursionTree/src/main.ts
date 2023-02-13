import { createApp } from 'vue'
import App from './App.vue'
import recursionTree from './components/recursionTree'


const app = createApp(App)
// @ts-ignore
.use(recursionTree)

app.mount('#app')
