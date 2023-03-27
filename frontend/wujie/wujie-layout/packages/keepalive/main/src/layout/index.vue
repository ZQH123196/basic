<template>
    <ElContainer mode="horizontal">
        <aside>
            <div><router-link to="/appA">appA</router-link></div>
            <div><router-link to="/appA/page">appA page</router-link></div>
        </aside>
        <main><RouterView name="appMain"></RouterView></main>
    </ElContainer>
</template>

<script setup lang="ts">
import { ElContainer } from "element-plus";
</script>
