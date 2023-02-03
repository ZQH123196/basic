<template>
    <WujieVue
        width="100%"
        height="100%"
        :name="wujieProps.name"
        :url="subUrl"
    ></WujieVue>
</template>

<script setup lang="ts">
import { watch } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();

const wujieProps = route.meta.wujieProps;
const routeProps = route.meta.routeProps as any;
let subUrl =
    routeProps.mode == "hash"
        ? wujieProps.url + "#/" + route.params.path
        : wujieProps.url + "" + route.params.path;
console.log("subUrl", subUrl);
</script>

<style lang="scss" scoped></style>
