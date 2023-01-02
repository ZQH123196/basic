<script setup lang="ts">
import { ElTree } from "element-plus";
import type {
    TreeNode4Front,
    TreeNode4Map,
    TreeNode4Array,
} from "./treeHelper.js";
import { walkTree4Map, walkTree4Array } from "./treeHelper.js";

const handleNodeClick = (data: TreeNode4Front) => {
    console.log(data);
};

const defaultProps = {
    children: "children",
    label: "label",
};

const data4Example: TreeNode4Front[] = [
    {
        label: "Level one 1",
        children: [
            {
                label: "Level two 1-1",
                children: [
                    {
                        label: "Level three 1-1-1",
                    },
                ],
            },
        ],
    },
    {
        label: "Level one 2",
        children: [
            {
                label: "Level two 2-1",
                children: [
                    {
                        label: "Level three 2-1-1",
                    },
                ],
            },
            {
                label: "Level two 2-2",
                children: [
                    {
                        label: "Level three 2-2-1",
                    },
                ],
            },
        ],
    },
];

let tree4FrontData: TreeNode4Front | [] = [];

// const multipleTree: TreeNode4Map = {
//     A: {
//         nodeName: "A",
//         nodePath: "A",
//         children: {
//             B: {
//                 nodeName: "B",
//                 nodePath: "AB",
//             },
//             C: {
//                 nodeName: "C",
//                 nodePath: "AC",
//                 children: {
//                     D: {
//                         nodeName: "D",
//                         nodePath: "ACD",
//                     },
//                     E: {
//                         nodeName: "E",
//                         nodePath: "ACE",
//                     },
//                     F: {
//                         nodeName: "F",
//                         nodePath: "ACF",
//                         children: {
//                             G: {
//                                 nodeName: "G",
//                                 nodePath: "ACFG",
//                             },
//                         },
//                     },
//                 },
//             },
//         },
//     },
// };

// walkTree4Map(multipleTree, tree4FrontData);

const srcJson = {
    TreeNodeVoList: [
        {
            nodeName: "A",
            nodePath: "A",
            children: [
                {
                    nodeName: "B",
                    nodePath: "AB",
                },
                {
                    nodeName: "C",
                    nodePath: "AC",
                    children: [
                        {
                            nodeName: "D",
                            nodePath: "ACD",
                        },
                        {
                            nodeName: "E",
                            nodePath: "ACE",
                        },
                        {
                            nodeName: "F",
                            nodePath: "ACF",
                            children: [
                                {
                                    nodeName: "G",
                                    nodePath: "ACFG",
                                },
                            ],
                        },
                    ],
                },
            ],
        },
    ],
};

const multipleTree: TreeNode4Array[] = srcJson.TreeNodeVoList;

walkTree4Array(multipleTree, tree4FrontData);
</script>

<template>
    <el-tree
        :data="tree4FrontData"
        :props="defaultProps"
        @node-click="handleNodeClick"
    ></el-tree>
</template>

<style scoped></style>
