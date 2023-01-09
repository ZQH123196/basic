<template>
    <el-container>
        <el-aside>
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
import { SideBarVue } from "./component";
</script>
