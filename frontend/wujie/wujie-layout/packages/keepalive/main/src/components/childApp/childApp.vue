<template>
  <WujieVue width="100%" height="100%" :name="wujieProps.name" :url="wujieProps.url" :sync="true"></WujieVue>
  
</template>

<script lang="ts" setup>import { useRoute, useRouter } from 'vue-router';


const route = useRoute();

const meta = route.meta
const wujieProps = meta.wujieProps
const instanceProps = route.meta.instanceProps;


if (instanceProps) {
    instanceProps.$router.push("/")
}

</script>

