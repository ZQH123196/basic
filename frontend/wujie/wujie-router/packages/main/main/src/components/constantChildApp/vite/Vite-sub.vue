<template>
  <!--单例模式，name相同则复用一个无界实例，改变url则子应用重新渲染实例到对应路由 -->
  <WujieVue width="100%" height="100%" name="vite" :url="subUrl"></WujieVue>
</template>

<script setup lang="ts">
import { computed } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const rootUrl = "//localhost:7501/"
let subUrl = computed(() => {
  const url = rootUrl + route.params.path
  console.log("computed dep inovke!", url);
  
  return url
});



</script>

<style lang="scss" scoped></style>
