<template>
    <!-- 跟子应用路由路径一模一样，hash 路径也跟子应用一样需要添加 # -->
    <!-- <router-link to="/system-auth">system-auth</router-link>
    <div class="sub-menu" v-show="true">
        <router-link to="/system-auth/#/home">home</router-link>
        <router-link to="/system-auth/#/childRoute"
        >childRoute</router-link>
        <router-link to="/system-auth/#/HelloWorldVue"
            >HelloWorldVue</router-link
        >
    </div> -->
    <template v-for="(item, i) in menuList" key="i">
        <div>
            <router-link :to="item.to" >{{ item.menuText }} : {{ item.to }}</router-link>
        </div>
    </template>

    <br />
    <!-- 因为引入的是相同的 component，都是同一个 key 会被复用，就导致 url 切换了，但是页面没有 unmount，然后重新挂载
        因此需要告知 vue 他们不是同一个对象，:key="$route.fullPath" 使得其无法复用 -->
    <router-view :key="$route.fullPath"></router-view>
</template>

<script lang="ts" setup>
import {
    RouteRecordNormalized,
    RouteRecordRaw,
    RouterLink,
    useRouter,
} from "vue-router";
import childAppVue from "./components/childApp/childApp.vue";
import WujieVue from "wujie-vue3";
import { h } from "vue";
import { setupApp } from "wujie";
import {lifecycles} from "./lifecycles";

const router = useRouter();
let menuList: {
    to: string;
    menuText: string;
}[] = [];

let childAppRegiisterList = [
    {
        mainRouteName: "appA",
        mainRoutePath: "/appA",
        menuText: "appA",
        wujieProps: {
            name: "appA",
            url: "//localhost:9000/",
            // exec: true,
            ...lifecycles,
            // alive: true,
        },
        childRoute: [""],
    },
    {
        mainRouteName: "appA-sub",
        mainRoutePath: "/appA/:path",
    },
    {
        mainRouteName: "appB",
        mainRoutePath: "/appB",
        menuText: "appB",
        wujieProps: {
            name: "appB",
            url: "//localhost:9001/",
            // exec: true,
            ...lifecycles,
            // alive: true,
        },
    },
];

let childrenRoute: RouteRecordRaw[] = [];
for (const item of childAppRegiisterList) {
    if (item.wujieProps) {
        setupApp(item.wujieProps);
    }
    childrenRoute.push({
        name: item.mainRouteName,
        path: item.mainRoutePath,
        // component: wujieChild,
        component: childAppVue,
        // component: c,
        meta: {
            registeName: item.wujieProps?.name,
            registeUrl: item.wujieProps?.url,
            menuText: item.menuText,
        },
    });
}

for (const childRoute of childrenRoute) {
    router.addRoute(childRoute);
}

menuList = mapRoutes2MenuList(router.getRoutes());

function mapRoutes2MenuList(routes: RouteRecordNormalized[]): typeof menuList {
    let menuList = routes
        .filter((route) => route.meta.menuText)
        .map((route) => {
            return {
                to: route.path,
                menuText: route.meta.menuText as string,
            };
        });
    return menuList;
}
</script>
