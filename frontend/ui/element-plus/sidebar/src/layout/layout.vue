<template>
    <el-container>
        <el-aside style="width: min-content">
            <el-scrollbar>
                <SideBarVue></SideBarVue>
            </el-scrollbar>
        </el-aside>
        <el-main>
            <RouterView></RouterView>
        </el-main>
    </el-container>
</template>

<script setup lang="ts">
import { SideBarVue } from "./components/";
</script>
