<template>
    <template v-for="(item, index) in refOptions">
        <li @click.stop="handleClickShow(item)">
            {{ item.label }}
            <ul v-if="item.children" v-show="item.testShow">
                <recursionTree :options="item.children" />
            </ul>
            <!-- 很简单，有 children 那就是 menu，没有就说明是可点击。默认 menu 点击只会展开。 -->
            <button v-else-if="item.url" v-show="item.testShow">
                <a>{{ item.url }}</a>
            </button>
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

// 这里很有意思，必须使用 ref 的 shadow proxy
const refOptions = ref(props.options);

const handleClickShow = (item) => {
    item.testShow = !item.testShow;
};

defineExpose({
    name: "recursionTree",
});
</script>
