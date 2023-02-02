<template>
  <!--保活模式，name相同则复用一个子应用实例，改变url无效，必须采用通信的方式告知路由变化 -->
  <WujieVue width="100%" height="100%" :name="meta.registeName" :url="meta.registeUrl" :sync="true"></WujieVue>

</template>

<script lang="ts" setup>import { useRoute, useRouter } from 'vue-router';


const route = useRoute();

const meta = route.meta
console.log("meta", meta);



</script>

