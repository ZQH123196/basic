<template>
    <template v-for="(item, index) in refOptions">
        <li>
            {{ item.label }}
            <ul v-if="item.children">
                <recursionTree :options="item.children" />
            </ul>
        </li>
    </template>
</template>

<script setup lang="ts">
import { ref } from "vue";
import recursionTree from ".";

const props = defineProps({
    options: {
        type: Array,
    },
});

const refOptions = ref(props.options);

defineExpose({
    name: "recursionTree",
});
</script>
