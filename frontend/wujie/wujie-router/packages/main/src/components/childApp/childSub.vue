<template>
    <!--保活模式，name相同则复用一个子应用实例，改变url无效，必须采用通信的方式告知路由变化 -->
    <WujieVue width="100%" height="100%" name="vue3" :url="subUrl"></WujieVue>
</template>

<script setup lang="ts">
import { watch } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
let subUrl = (route.meta.registeUrl as string) + route.params.path;

watch(route, () => {
    console.log("child-sub-router-change", `/${route.params.path}`);
});
</script>

<style lang="scss" scoped></style>
