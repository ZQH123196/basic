<template>
    <template v-for="(item, index) in options">
        <ElSubMenu v-if="item.children" :index="item.path">
            <template #title>{{ item.path }}</template>
            <recursionTreeVue
                :options="setPahtEnumAndReturn(item.pathEnum, item.children)"
            ></recursionTreeVue>
        </ElSubMenu>
        <ElMenuItem v-else :index="item.path" @click="handleClick(item.pathEnum)">{{
            // item.meta.menuProps.title
            item.pathEnum
        }}</ElMenuItem>
    </template>
</template>

<script setup lang="ts">
import { ElMenuItem, ElSubMenu } from "element-plus";
import { recursionTreeVue } from ".";
import { useRouter } from "vue-router";
import { menuTypePathEnum } from "./type";

const router = useRouter();
const props = defineProps<{
    options: menuTypePathEnum[];
}>();

function handleClick(path: string) {
    router.push(path)
}

const options = props.options;

/**
 * 如果 pathEnum 已经有值，就说明此 pathEnum 是由父级交给子级的值，将子级当前的路径加上
 * 如果 pathEnum 没赋值就说明是根，将跟赋值给 pathEnum
 */
options.forEach((item) => {
    if (!item.pathEnum) {
        item.pathEnum = item.path;
    }
});

/**
 * 根据路径遍历组合出最终可用的 path
 * @param curPathEnum 当前的路径遍历值
 * @param childOptions
 */
function setPahtEnumAndReturn(
    curPathEnum: string,
    childOptions: menuTypePathEnum[]
) {
    return childOptions.map((child) => {
        let pathEnum = "";
        if (curPathEnum) {
            if (curPathEnum.endsWith("/")) pathEnum = curPathEnum + child.path;
            else pathEnum = curPathEnum + "/" + child.path;
        }
        return {
            ...child,
            pathEnum,
        };
    });
}
</script>
