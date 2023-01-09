<template>
    <ElButton @click="syncRouter">请求路由并渲染菜单</ElButton>
    <el-menu default-active="1">
        <template v-for="value in menuList.list">
            <el-menu-item
                :index="value.order.toString()"
                @click="handleMenuItemClick(value.path)"
            >
                <el-icon><icon-menu /></el-icon>
                <span>{{ value.menuText }}</span>
            </el-menu-item>
        </template>
    </el-menu>
</template>

<script lang="ts" setup>
import getRemoteRoute from "@/api/getRemoteRoute";
import { Menu as IconMenu } from "@element-plus/icons-vue";
import { reactive } from "vue";
import { Router, RouteRecordRaw, useRouter } from "vue-router";
import { MenuListType, MenuItemType } from "./type";

const router = useRouter();

/**
 * 编程式导航
 * @param path
 */
function handleMenuItemClick(path: string) {
    router.push(path);
}
console.log(router.getRoutes());

// 里面包裹的 list 才是真正的值，之所以要包裹是为了赋值方便，避免直接覆盖 reactive
let menuList: MenuListType = reactive({ list: [] });
// 初始化渲染菜单
menuList.list = nestedRoute2MenuList(router.getRoutes());
console.log("menuList.list", menuList.list);

// vue3 似乎不能直接监听路由表的变更了 getRoutes() 是个函数，不能监听
// router.options.routes 是初始化是的路由，始终保持 initial 状态
// 所以只能在添加动态路由后主动触发一次菜单渲染了
const syncRouter = () => {
    const curRoutes = router.getRoutes();
    console.log("router.getRoutes()", router.getRoutes());
    const dynamicRoute: RouteRecordRaw[] = getRemoteRoute();
    addDynamicRoute(dynamicRoute, curRoutes);
    menuList.list = nestedRoute2MenuList(curRoutes);
    
    console.log("router.getRoutes()", router.getRoutes());
};

/**
 * simpleRouteList 指没有 children 的
 * nestedMenuList 指有 children
 * 
 * 其实 vue route 已经自动将嵌套的路由给 flatten 了，打印一下 router.getRoutes() 便知
 * 所以可以省略嵌套路由的转换，直接将 simpleRouteList 给显示就行
 * @param curRoutes
 */
function nestedRoute2MenuList(curRoutes: RouteRecordRaw[]) {
    let simpleRouteList = curRoutes.filter((route) => route.children && route.children.length == 0);
    let nestedRouteList = curRoutes.filter((route) => route.children && route.children.length > 0);
    console.log("simpleRouteList", simpleRouteList);
    console.log("nestedRouteList", nestedRouteList);

    let simpleMenuList = simpleRouteList.map(routeItem2Menu);
    // let nestedMenuList = nestedRouteItem2Menu(nestedRouteList);

    let menuList: MenuItemType[] = [];
    menuList = menuList.concat(simpleMenuList);
    // menuList.concat(nestedMenuList);

    return menuList;
}

function routeItem2Menu(simpleRoute: RouteRecordRaw): MenuItemType {
    return {
        path: simpleRoute.path,
        order: simpleRoute.meta?.order as number,
        menuText: simpleRoute.meta?.menuText as string,
    };
}

function nestedRouteItem2Menu(
    nestedRouteList: RouteRecordRaw[]
): MenuItemType[] {
    let menuItemList: MenuItemType[] = [];
    nestedRouteList.forEach((route) =>
        toSimpleRouteRecursion(route, route.path, menuItemList)
    );

    return menuItemList;
}

/**
 * 相当于深度遍历，拼接从根到每个叶子节点的 path，并结合叶子节点的值最终返回
 */
function toSimpleRouteRecursion(
    route: RouteRecordRaw,
    path: string,
    menuItemList: MenuItemType[]
) {
    if (!route.children) {
        path += route.path;
        let simpleRoute: MenuItemType = {
            path: path,
            order: route.meta?.order as number,
            menuText: route.meta?.menuText as string,
        };
        menuItemList.push(simpleRoute);
        return;
    }

    for (const childRoute of route.children) {
        toSimpleRouteRecursion(childRoute, childRoute.path, menuItemList);
    }
}

function addDynamicRoute(
    dynamicRoute: RouteRecordRaw[],
    curRoutes: RouteRecordRaw[]
) {
    dynamicRoute.forEach((route) => {
        const testSameRoute = curRoutes.find((_route) => {
            return _route.path == route.path;
        });
        // 没有相同路径的路由才添加
        if (!testSameRoute) {
            router.addRoute(route);
        }
    });
}
</script>
