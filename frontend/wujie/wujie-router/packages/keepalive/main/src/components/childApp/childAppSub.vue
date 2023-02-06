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
const vueProps = route.meta.vueProps;

if (vueProps) {
    vueProps.instance.config.globalProperties.$router.push(route.params.path as string)
}

</script>

<style lang="scss" scoped></style>
