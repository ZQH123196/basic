import { App } from 'vue';
import recursionTree from './index.vue';

recursionTree.install = (app: App<Element>) => {
    app.component(recursionTree.name, recursionTree)
}


export default recursionTree