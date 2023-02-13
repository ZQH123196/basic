<template>
    <!-- <el-icon><Expand /></el-icon>
  <el-icon><Fold /></el-icon> -->
    <div style="padding: 0 15px" @click="toggleClick">
        <el-icon size="20">
            <Expand v-if="isActive" />
            <Fold v-else />
        </el-icon>
    </div>
</template>

<script setup lang="ts">
import { defineProps, defineEmits } from "vue";

defineProps({
    isActive: {
        type: Boolean,
        default: false,
    },
});

const emit = defineEmits(["toggleClick"]);
const toggleClick = () => {
    emit("toggleClick");
};
</script>

<style scoped></style>
