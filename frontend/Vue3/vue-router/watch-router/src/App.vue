<script setup lang="ts">
import { useRoute } from 'vue-router';
import SideBar from './components/SideBar.vue'

window.route = useRoute();
</script>

<template>
  <SideBar></SideBar>
  
</template>

<style scoped>
</style>
