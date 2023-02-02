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
        <div><router-link :to="item.to">{{ item.menuText }}</router-link></div>
    </template>

    <br/>

    <router-view></router-view>
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

const router = useRouter();
let menuList: {
    to: string;
    menuText: string;
}[] = [];

let childRegiste = {
    mainRouteName: "A",
    mainRoutePath: "/A",
    registeName: "system-auth",
    registeUrl: "//localhost:8999/cors",
    menuText: "menuText",
};

const template = `
<template>
    <WujieVue width="100%" height="100%" name="${childRegiste.registeName}" url="${childRegiste.registeUrl}" sync="true"></WujieVue>
    `

const wujieChild = {
    template: template,
};

let w = h(WujieVue, {width: "100%", height: "100%", name: childRegiste.registeName, url: childRegiste.registeUrl, stnc: true}, []);
let c = h(w)



let childRoute: RouteRecordRaw = {
    name: childRegiste.mainRouteName,
    path: childRegiste.mainRoutePath,
    // component: wujieChild,
    component: childAppVue,
    // component: c,
    meta: {
        registeName: childRegiste.registeName,
        registeUrl: childRegiste.registeUrl,
        menuText: childRegiste.menuText,
    },
};
router.addRoute(childRoute);
menuList = mapRoutes2MenuList(router.getRoutes());

function mapRoutes2MenuList(routes: RouteRecordNormalized[]): typeof menuList {
    let menuList = routes.map((route) => {
        return {
            to: route.path,
            menuText: route.meta.menuText as string,
        };
    });
    return menuList;
}
</script>
