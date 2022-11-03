<script setup lang="ts">

const handleObj = {
  preFilter: () => {
    console.log("preFilter");
  },
  postHandle: () => {
    console.log("postHandle");
  }
}

const handleClick = () => {
  console.log("子应用 handleClick");
  window?.$wujie.bus.$emit('registerSidebar', handleObj)
}

</script>

<template>
  <h1>system-auth</h1>
  <button @click="handleClick()">registerSidebar</button>
</template>

