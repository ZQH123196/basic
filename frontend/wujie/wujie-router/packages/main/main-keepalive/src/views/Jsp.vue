<template>
    <h1>JSP 页面</h1>
    <!--保活模式，name相同则复用一个子应用实例，改变url无效，必须采用通信的方式告知路由变化 -->
    <WujieVue width="100%" height="100%" :name="EnumServer.nameJspUrl" :sync="true"></WujieVue>
</template>

<script setup lang="ts">
import EnumServer from '../constant/serverEnum';

    
</script>

<style lang="scss" scoped></style>
