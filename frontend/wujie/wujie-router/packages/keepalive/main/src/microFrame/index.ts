import { App } from "vue";
import { childAppList, setupMicroFrameApp } from "./setupChildApp";

declare module 'vue-router' {
    interface RouteMeta {
        vueProps?: {
            instance: App<Element>
        },
        routeMode: "history" | "hash"
        // 是可选的
        menuProps?: {
            text: string
        }
        // 每个路由都必须声明
        wujieProps: any
    }
}



export {
    childAppList,
    setupMicroFrameApp
}