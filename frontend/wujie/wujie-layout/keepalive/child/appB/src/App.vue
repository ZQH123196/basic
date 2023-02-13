<script setup lang="ts">import { useRoute } from 'vue-router';

const route = useRoute();
</script>

<template>
  <h1>appB</h1>

  <div>child route.fullPath = [{{ route.fullPath }}]</div>
  <router-view :key="$route.fullPath"></router-view>
</template>
