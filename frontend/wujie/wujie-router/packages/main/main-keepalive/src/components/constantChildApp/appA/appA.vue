<template>
  <WujieVue width="100%" height="100%" name="appA" url="//localhost:9000/" :sync="true"></WujieVue>


</template>

<script lang="ts" setup>import { useRoute, useRouter } from 'vue-router';


const route = useRoute();

const meta = route.meta
const wujieProps = meta.wujieProps



</script>

