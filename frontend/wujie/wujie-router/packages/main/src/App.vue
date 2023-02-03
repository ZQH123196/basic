<template>
    <!-- 跟子应用路由路径一模一样，hash 路径也跟子应用一样需要添加 #
    <div><router-link to="/system-auth">system-auth</router-link></div>
    <div><router-link to="/system-auth/#/home">home</router-link></div>
    <div><router-link to="/system-auth/#/childRoute">childRoute</router-link></div>
    <div><router-link to="/system-auth/#/HelloWorldVue">HelloWorldVue</router-link></div> -->

    <div><router-link to="/vite">vite</router-link></div>
    <div><router-link to="/vite-sub/home">home</router-link></div>
    <div><router-link to="/vite-sub/dialog">dialog</router-link></div>
    <div><router-link to="/vite-sub/location">location</router-link></div>
    <div><router-link to="/vite-sub/contact">contact</router-link></div>

    <br />
    <br />
    
    <div><router-link to="/appA">appA</router-link></div>
    <div><router-link to="/appA/page">appA page</router-link></div>

    <br />
    <br />

    <template v-for="(item, i) in menuList" :key="i">
        <div>
            <router-link :to="item.to"
                >{{ item.menuText }} : {{ item.to }}</router-link
            >
        </div>
    </template>

    <br />
    <br />

    <!-- 因为引入的是相同的 component，都是同一个 key 会被复用，就导致 url 切换了，但是页面没有 unmount，然后重新挂载
        因此需要告知 vue 他们不是同一个对象，:key="$route.fullPath" 使得其无法复用 -->

    <div>route.fullPath = [{{ route.fullPath }}]</div>
    <router-view :key="$route.fullPath"></router-view>
</template>

<script lang="ts" setup>
import {
    RouteRecordNormalized,
    RouteRecordRaw,
    RouterLink,
    useRoute,
    useRouter,
} from "vue-router";
import childAppVue from "./components/childApp/childApp.vue";
import { setupApp } from "wujie";
import { lifecycles } from "./lifecycles";
import ChildSubVue from "./components/childApp/childSub.vue";

const router = useRouter();
const route = useRoute();

let menuList: {
    to: string;
    menuText: string;
}[] = [];

// function credentialsFetch(url, options) {
//     return window.fetch(url, { ...options, credentials: "omit" });
// }

// const routeModeHashConstant = "hash";
// let childAppRegiisterList = [
//     {
//         mainRouteName: "appA",
//         mainRoutePath: "/appA",
//         routeProps: {
//             mode: "history",
//             childPath: "",
//         },
//         menuProps: {
//             text: "appA",
//         },
//         wujieProps: {
//             name: "appA",
//             url: "//localhost:9000/",
//             fetch: credentialsFetch,
//             exec: true,
//             ...lifecycles,

//         },
//     },
//     {
//         mainRoutePath: "/appA",
//         routeProps: {
//             mode: "history",
//             childPath: "page",
//         },
//         menuProps: {
//             text: "appA page",
//         },
//     },
//     {
//         mainRouteName: "appB",
//         mainRoutePath: "/appB",
//         routeProps: {
//             mode: routeModeHashConstant,
//             childPath: "home",
//         },
//         menuProps: {
//             text: "appB",
//         },
//         wujieProps: {
//             name: "appB",
//             url: "//localhost:9001/",
//             fetch: credentialsFetch,
//             exec: true,
//             ...lifecycles,
//             alive: true,
//         },
//     },
// ];

// let childrenRoute: RouteRecordRaw[] = [];
// // 有 wujieProps 的注册进路由，并生成 :path 的子路由
// for (const item of childAppRegiisterList) {
//     if (!item.wujieProps) {
//         continue;
//     }

//     setupApp(item.wujieProps);
//     childrenRoute.push({
//         name: item.mainRouteName,
//         path: item.mainRoutePath,
//         component: childAppVue,
//         meta: {
//             routeProps: item.routeProps,
//             menuProps: item.menuProps,
//             wujieProps: item.wujieProps,
//         },
//     });
//     // 添加子应用的子路由路径拦截
//     childrenRoute.push({
//         name: item.mainRouteName + "-sub",
//         path: item.mainRoutePath + "/:path",
//         // path: item.mainRoutePath + "/:pathMatch(.*)*",
//         component: ChildSubVue,
//         meta: {
//             routeProps: item.routeProps,
//             wujieProps: item.wujieProps,
//         },
//     });
// }

// for (const childRoute of childrenRoute) {
//     router.addRoute(childRoute);
// }

// menuList = mapChildRegisters2MenuList(childAppRegiisterList);

// function mapChildRegisters2MenuList(
//     childAppRegiisterList: Array<any>
// ): typeof menuList {
//     let menuList = childAppRegiisterList
//         .filter((item) => item.menuProps && item.routeProps)
//         .map((item) => {
//             let to =
//                 item.routeProps.mode == routeModeHashConstant
//                     ? item.mainRoutePath + "/#/" + item.routeProps.childPath
//                     : item.mainRoutePath + "/" + item.routeProps.childPath;

//             return {
//                 to,
//                 menuText: item.menuProps.text,
//             };
//         });
//     return menuList;
// }
</script>
