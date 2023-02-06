<template>
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
import { Menu as IconMenu } from "@element-plus/icons-vue";
import { reactive } from "vue";
import { useRouter } from "vue-router";
import { MenuListType, } from "./type";
import { nestedRoute2MenuList } from "./utils";

const router = useRouter();

/**
 * 编程式导航
 * @param path
 */
function handleMenuItemClick(path: string) {
    router.push(path);
}

// 里面包裹的 list 才是真正的值，之所以要包裹是为了赋值方便，避免直接覆盖 reactive
let menuList: MenuListType = reactive({ list: [] });
// 初始化渲染菜单
menuList.list = nestedRoute2MenuList(router.getRoutes());






</script>
