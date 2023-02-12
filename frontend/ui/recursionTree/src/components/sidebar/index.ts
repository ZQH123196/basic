
import {recursionTreeVue} from "./recursionTree"
import sidebarVue from "./index.vue"
import type { App } from "vue"

// 提前注册 recursionTree，是为了子组件能够 import 自己，
// 不提前注册会没有定义，就会导致递归前提中的 自己调用自己 的条件不满足
sidebarVue.install = (app: App<Element>) => {
    app.component("recursionTreeVue", recursionTreeVue)    
}


export default sidebarVue