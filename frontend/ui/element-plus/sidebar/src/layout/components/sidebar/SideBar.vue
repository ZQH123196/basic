<template>
    <!-- <ElRadioGroup v-model="isCollapse" style="margin-bottom: 20px">
        <ElRadioButton :label="false">expand</ElRadioButton>
        <ElRadioButton :label="true">collapse</ElRadioButton>
    </ElRadioGroup> -->
    <Hamburger
        id="hamburger-container"
        :is-active="isCollapse"
        class="hamburger-container"
        @toggleClick="toggleSideBar"
    />
    <ElMenu class=".el-menu-sidebar" :collapse="isCollapse">
        <recursionTree :options="treeData"> </recursionTree>
    </ElMenu>
</template>

<script lang="ts" setup>
import { ElMenu, ElRadioButton, ElRadioGroup } from "element-plus";
import { ref } from "vue";
import Hamburger from "../Hamburger/index.vue";
import recursionTree from "../recursionTree";

const isCollapse = ref(false);
const toggleSideBar = () => {
    isCollapse.value = !isCollapse.value;
};

const treeData = [
    {
        label: "一级/1",
        meta: {
            iconProps: {
                svg: {
                    svgProps: {},
                    paths: [
                        {
                            d: "M10 6C10 4.89543 10.8954 4 12 4H36C37.1046 4 38 4.89543 38 6V44L31 39L24 44L17 39L10 44V6Z",
                            fill: "none",
                            stroke: "#333",
                            "stroke-width": "4",
                            "stroke-linecap": "round",
                            "stroke-linejoin": "round",
                        },
                        {
                            d: "M18 22L30 22",
                            stroke: "#333",
                            "stroke-width": "4",
                            "stroke-linecap": "round",
                            "stroke-linejoin": "round",
                        },
                        {
                            d: "M18 30L30 30",
                            stroke: "#333",
                            "stroke-width": "4",
                            "stroke-linecap": "round",
                            "stroke-linejoin": "round",
                        },
                        {
                            d: "M18 14L30 14",
                            stroke: "#333",
                            "stroke-width": "4",
                            "stroke-linecap": "round",
                            "stroke-linejoin": "round",
                        },
                    ],
                },
            },
        },
        children: [
            {
                label: "一级/1-二级/1",
            },
            {
                label: "一级/1-二级/2",
                children: [
                    {
                        label: "一级/1-二级/2-三级/1",
                        url: "do something!",
                        meta: {
                            iconProps: {
                                svg: {
                                    paths: [
                                        {
                                            d: "M9.5 1.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0ZM6 5.5l-4.535-.442A.531.531 0 0 1 1.531 4H14.47a.531.531 0 0 1 .066 1.058L10 5.5V9l.452 6.42a.535.535 0 0 1-1.053.174L8.243 9.97c-.064-.252-.422-.252-.486 0l-1.156 5.624a.535.535 0 0 1-1.053-.174L6 9V5.5Z",
                                        },
                                    ],
                                },
                            },
                        },
                    },
                    { label: "一级/1-二级/2-三级/2", url: "do something!" },
                    { label: "一级/1-二级/2-三级/3", url: "do something!" },
                ],
            },
        ],
    },
    {
        label: "一级/2",
        meta: {
            iconProps: {
                "@element-plus/icons-vue": {
                    iconName: "",
                },
            },
        },
        children: [
            {
                label: "一级/2-二级/1",
            },
            {
                label: "一级/2-二级/2",
                children: [
                    { label: "一级/2-二级-三级/1", url: "do something!" },
                    { label: "一级/2-二级-三级/2", url: "do something!" },
                    { label: "一级/2-二级-三级/3", url: "do something!" },
                ],
            },
        ],
    },
];
</script>

<style>
.el-menu-sidebar:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
}
</style>
