<template>
    <WujieVue width="100%" height="100%" name="appA"></WujieVue>
</template>

<script setup lang="ts">
import { computed, onMounted } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
// const rootUrl = "//localhost:9000/"
// let subUrl = computed(() => rootUrl + route.params.path)
const wujieProps = route.meta.wujieProps;
console.log("appA.vue wujieProps", wujieProps);
console.log("main route.params.path", route.params.path);
// if (childVueInstance.appA) {
//     console.log("main childVueInstance", childVueInstance);
//     const childRouter = childVueInstance.appA.config.globalProperties.$router;
//     childRouter.push(`/${route.params.path}`);
// }
</script>

<style lang="scss" scoped></style>
