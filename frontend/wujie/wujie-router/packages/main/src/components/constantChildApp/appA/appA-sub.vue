<template>
    <WujieVue
        width="100%"
        height="100%"
        name="appA"
        :url="subUrl"
    ></WujieVue>
</template>

<script setup lang="ts">
import { useRoute } from "vue-router";

const route = useRoute();


const rootUrl = "//localhost:9000/"
let subUrl = rootUrl + route.params.path;
console.log("subUrl", subUrl);

</script>

<style lang="scss" scoped></style>
