import Four from "@/layout/component/sidebar/child/Four.vue";
import { RouteRecordRaw } from "vue-router";


export default function getRemoteRoute(): RouteRecordRaw[] {
    return [
        {
            path: "/Four",
            component: Four,
            meta: {
                order: 4,
                menuText: "Navigator Four",
            },
        },
    ];
}