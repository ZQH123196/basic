<template>
    <ElMenu>
        <!-- 无限层级 -->
        <recursionTreeVue :options="options"></recursionTreeVue>
    </ElMenu>
</template>

<script setup lang="ts">
import { ElMenu } from "element-plus";
import { menuTypePathEnum } from "./recursionTree/type";
import { recursionTreeVue } from "./recursionTree";

const props = defineProps<{options: menuTypePathEnum[];}>()

const options = props.options

</script>
