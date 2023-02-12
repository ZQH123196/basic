<template>

    <!-- 因为引入的是相同的 component，都是同一个 key 会被复用，就导致 url 切换了，但是页面没有 unmount，然后重新挂载
        因此需要告知 vue 他们不是同一个对象，:key="$route.fullPath" 使得其无法复用 -->
    <!-- <div>main route.fullPath = [{{ route.fullPath }}]</div> -->
    
    <router-view :key="$route.fullPath"></router-view>
</template>

<script lang="ts" setup>

import { RouterLink, useRoute, useRouter } from "vue-router";

const route = useRoute();



</script>
