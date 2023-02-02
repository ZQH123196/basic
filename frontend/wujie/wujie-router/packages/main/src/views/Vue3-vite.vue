<template>
  <!--保活模式，name相同则复用一个子应用实例，改变url无效，必须采用通信的方式告知路由变化 -->
  <WujieVue width="100%" height="100%" name="vue3-vite" :url="vue3ViteUrl" :sync="true"></WujieVue>
</template>

<script>
export default {
  data() {
    return {
      vue3ViteUrl: "//localhost:9000/",
    };
  },
};
</script>

<style lang="scss" scoped></style>
