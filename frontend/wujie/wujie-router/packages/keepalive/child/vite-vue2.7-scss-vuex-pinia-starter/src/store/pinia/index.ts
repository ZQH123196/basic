import { defineStore, createPinia } from 'pinia'
import Vue from 'vue';

const useStore = defineStore('storeId', {
    // 推荐使用 完整类型推断的箭头函数
    state: () => {
        return {
            // 所有这些属性都将自动推断其类型
            counter: 0,
            name: 'Eduardo',
            isAdmin: true,
        }
    },
})

const pinia = createPinia()
Vue.use(pinia)

export const piniaStore = {
    useStore
}