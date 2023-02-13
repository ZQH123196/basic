export const treeData = [
    {
        label: "一级/1",
        children: [
            {
                label: "一级/1-二级/1",
            },
            {
                label: "一级/1-二级/2",
                children: [
                    { label: "一级/1-二级/2-三级/1", url: "do something!" },
                    { label: "一级/1-二级/2-三级/2", url: "do something!" },
                    { label: "一级/1-二级/2-三级/3", url: "do something!" },
                ],
            },
        ],
    },
    {
        label: "一级/2",
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