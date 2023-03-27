<template>
    <WujieVue
        width="100%"
        height="100%"
        :name="wujieProps.name"
    ></WujieVue>
</template>

<script setup lang="ts">
import { useRoute } from "vue-router";

const route = useRoute();

const wujieProps = route.meta.wujieProps;
const instanceProps = route.meta.instanceProps;

// wujie 将子应用的路径匹配放到 route.params.path 
if (instanceProps) {
    instanceProps.$router.push(route.params.path as string)
}

</script>

<style lang="scss" scoped></style>
