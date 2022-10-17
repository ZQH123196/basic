<template>
    <el-container>

        <el-aside>
            <side-bar />
        </el-aside>
        <el-main>
            <router-view />
        </el-main>
        
    </el-container>
</template>


<script lang="ts" setup>
import SideBar from "@/views/layout/components/SideBar/SideBar.vue"


</script>