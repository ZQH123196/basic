<template>
    <template v-for="(item, index) in refOptions">
        <ElSubMenu v-if="item.children" :index="item.label + index">
            <template
                #title
                style="
                    display: flex;
                    align-items: center;
                    justify-content: center;
                "
            >
                <el-icon>
                    <svg
                        v-if="item.meta?.iconProps?.svg?.paths"
                        xmlns="http://www.w3.org/2000/svg"
                        width="16"
                        height="16"
                        fill="currentColor"
                        class="bi bi-x-square-fill"
                        viewBox="0 0 16 16"
                    >
                        <template
                            v-for="pathAttr in item.meta.iconProps.svg.paths"
                        >
                            <path v-bind="pathAttr" />
                        </template>
                    </svg>
                </el-icon>
                <span>{{ item.label }}</span>
            </template>
            <template v-if="item.children" v-show="item.testShow">
                <recursionTree :options="item.children" />
            </template>
        </ElSubMenu>
        <!-- 很简单，有 children 那就是 menu，没有就说明是可点击。默认 menu 点击只会展开。 -->
        <el-menu-item
            v-else
            :index="item.label + index"
            @click="handleClickMenuItem(item.url)"
        >
            <template #title>
                <div
                    style="
                        display: flex;
                        align-items: center;
                        justify-content: center;
                    "
                >
                    <svg
                        v-if="item.meta?.iconProps?.svg?.paths"
                        xmlns="http://www.w3.org/2000/svg"
                        width="16"
                        height="16"
                        fill="currentColor"
                        class="bi bi-x-square-fill"
                        viewBox="0 0 16 16"
                    >
                        <template
                            v-for="pathAttr in item.meta.iconProps.svg.paths"
                        >
                            <path v-bind="pathAttr" />
                        </template>
                    </svg>
                    <span>{{ item.label }}</span>
                </div>
            </template>
        </el-menu-item>
    </template>
</template>

<!-- 为了实现递归组件需要定义名字 -->
<script lang="ts">
export default {
    name: "recursionTree",
};
</script>

<script setup lang="ts">
import { ref, defineProps, render, h } from "vue";
import { useRouter } from "vue-router";
import recursionTree from ".";



const props = defineProps({
    options: {
        type: Array,
    },
});

const router = useRouter();

// 这里很有意思，必须使用 ref 的 shadow proxy
const refOptions: any = ref(props.options);

const handleClickMenuItem = (url: string) => {
    if (!url) throw new Error(`url[${url}] 不存在，无法跳转！`);

    router.push(url);
};
</script>
