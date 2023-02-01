<template>
    <ElButton @click="syncRouter">请求路由并渲染菜单</ElButton>
    <el-row>
        <el-col :span="12">
            <el-menu default-active="1">
                <template v-for="value in MenuList.list">
                    <el-menu-item
                        :index="value.order.toString()"
                        @click="handleMenuItemClick(value.path)"
                    >
                        <el-icon><icon-menu /></el-icon>
                        <span>{{ value.menuText }}</span>
                    </el-menu-item>
                </template>
            </el-menu>
        </el-col>
        <el-col :span="12">
            <RouterView></RouterView>
        </el-col>
    </el-row>
</template>

<script lang="ts" setup>
import getRemoteRoute from "@/api/getRemoteRoute";
import { Menu as IconMenu } from "@element-plus/icons-vue";
import { reactive, watch } from "vue";
import { RouteLocationNormalizedLoaded, Router, RouteRecordRaw, useRoute, useRouter } from "vue-router";
import { MenuListType } from "./type";

const router = useRouter();

/**
 * 编程式导航
 * @param path
 */
function handleMenuItemClick(path: string) {
    router.push(path);
}

let MenuList: MenuListType = reactive({ list: [] });
// 初始化渲染菜单
MenuList.list = route2MenuList(router);

// vue3 似乎不能直接监听路由表的变更了 getRoutes() 是个函数，不能监听
// router.options.routes 是初始化是的路由，始终保持 initial 状态
// 所以只能在添加动态路由后主动触发一次菜单渲染了
const syncRouter = () => {
    console.log("router.getRoutes()", router.getRoutes());
    const dynamicRoute: RouteRecordRaw[] = getRemoteRoute();
    addDynamicRoute(dynamicRoute);
    MenuList.list = route2MenuList(router);
    console.log("router.getRoutes()", router.getRoutes());
};

function route2MenuList(router: Router) {
    return router.getRoutes().map(({ path, meta: { order, menuText } }) => {
        return {
            order: order as number,
            menuText: menuText as string,
            path,
        };
    });
}

function addDynamicRoute(dynamicRoute: RouteRecordRaw[]) {
    const curRoutes = router.getRoutes();
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
