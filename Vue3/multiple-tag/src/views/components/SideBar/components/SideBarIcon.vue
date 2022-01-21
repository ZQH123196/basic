<script setup lang="ts">


const props = defineProps<{
    text: string
}>()



</script>


<template>
    <div class="group">
        {{props.text}}
        <div class="siderbar-tooltip group-hover:scale-100">tooltip 💡</div>
    </div>
</template>