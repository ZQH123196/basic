<template>
    <WujieVue
        width="100%"
        height="100%"
        :name="wujieProps.name"
    ></WujieVue>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from "vue-router";
const route = useRoute();



const wujieProps = route.meta.wujieProps;
const instanceProps = route.meta.instanceProps;

// route :path 将子应用的路径匹配放到 route.params.path 
// route :pathmatch() 将子应用的路径匹配放到 route.params.pathMatch
if (instanceProps) {
    instanceProps.$router.push(route.params.pathMatch[0] as string)
}



</script>

<style lang="scss" scoped></style>
