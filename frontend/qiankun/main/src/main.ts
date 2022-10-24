import './public-path';
import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { registerMicroApps, start } from 'qiankun';

createApp(App).mount('#app')


registerMicroApps([
    {
        name: 'a-child',
        entry: '//localhost:3000',
        container: '#container',
        activeRule: '/app-react',
    },
]);
// 启动 qiankun
start();