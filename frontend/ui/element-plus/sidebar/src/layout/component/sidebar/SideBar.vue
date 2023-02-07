<template>
    <el-menu default-active="1">
        <el-menu-item-group title="Group One">
            <el-menu-item index="1-1">item one</el-menu-item>
            <el-menu-item index="1-2">item two</el-menu-item>
        </el-menu-item-group>
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
import { Menu as IconMenu } from "@element-plus/icons-vue";
import { useRouter } from "vue-router";
import { menuList } from "./share";
import { mapRoutes2MenuList } from "./utils";

/**
 * 动态更新路由的地方在 permission.ts
 */

const router = useRouter();

/**
 * 编程式导航
 * @param path
 */
function handleMenuItemClick(path: string) {
    router.push(path);
}

// 初始化渲染菜单
menuList.list = mapRoutes2MenuList(router.getRoutes());
</script>
