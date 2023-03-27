<template>
    <WujieVue
        width="100%"
        height="100%"
        name="appA"
        url="//localhost:9000/"
        :sync="true"
    ></WujieVue>
</template>

<script lang="ts" setup>
import { onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";

const route = useRoute();


const wujieProps = route.meta.wujieProps;
console.log("appA.vue wujieProps", wujieProps);

// childVueInstance 没有值的时候，说明是第一点击，子应用还没有能设置值
// if (childVueInstance.appA) {
//     console.log("appA.vue childVueInstance", childVueInstance);
//     const childRouter = childVueInstance.appA.config.globalProperties.$router;
//     childRouter.push(`/`);
// }
</script>
