<template>
    <WujieVue
        width="100%"
        height="100%"
        name="appA"
        :url="subUrl"
    ></WujieVue>
</template>

<script setup lang="ts">
import { computed } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();


const rootUrl = "//localhost:9000/"
let subUrl = computed(() => rootUrl + route.params.path)

</script>

<style lang="scss" scoped></style>
