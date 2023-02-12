<script setup lang="ts">
import RecursionTree from "./components/recursionTree";
import {treeData} from "./treeData"

</script>

<template>
    <RecursionTree :options="treeData"></RecursionTree>
</template>
