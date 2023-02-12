import { menuType } from "@/components/sidebar/recursionTree";
import { defineStore } from "pinia";

interface State {
    sidebarData: menuType[]
}


export const useSidebarStore = defineStore("sidebar", {
    state: (): State => ({
        sidebarData: [
            {
                name: "",
                path: "/A-1",
                meta: {
                    menuProps: {
                        title: "一级-1",
                    },
                },
                children: [
                    {
                        name: "",
                        path: "B-1",
                        meta: {
                            menuProps: {
                                title: "二级-1",
                            },
                        },
                    },
                    {
                        name: "",
                        path: "B-2",
                        meta: {
                            menuProps: {
                                title: "二级-2",
                            },
                        },
                        children: [
                            {
                                name: "",
                                path: "C-1",
                                meta: {
                                    menuProps: {
                                        title: "三级-1",
                                    },
                                },
                            },
                            {
                                name: "",
                                path: "C-2",
                                meta: {
                                    menuProps: {
                                        title: "三级-2",
                                    },
                                },
                            },
                        ],
                    },
                ],
            },
            {
                name: "",
                path: "/A-2",
                meta: {
                    menuProps: {
                        title: "一级-2",
                    },
                },
            },
        ]
    })
})







// {
//     menuProps: [
//         {
//             name: "",
//             path: "/A-1",
//             meta: {
//                 menuProps: {
//                     title: "一级-1",
//                 },
//             },
//             children: [
//                 {
//                     name: "",
//                     path: "/A-1/B-1",
//                     meta: {
//                         menuProps: {
//                             title: "二级-1",
//                         },
//                     },
//                 },
//                 {
//                     name: "",
//                     path: "/A-1/B-2",
//                     meta: {
//                         menuProps: {
//                             title: "二级-2",
//                         },
//                     },
//                     children: [
//                         {
//                             name: "",
//                             path: "/A-1/B-2/C-1",
//                             meta: {
//                                 menuProps: {
//                                     title: "三级-1",
//                                 },
//                             },
//                         },
//                         {
//                             name: "",
//                             path: "/A-1/B-2/C-2",
//                             meta: {
//                                 menuProps: {
//                                     title: "三级-2",
//                                 },
//                             },
//                         },
//                     ],
//                 },
//             ],
//         },
//         {
//             name: "",
//             path: "/A-2",
//             meta: {
//                 menuProps: {
//                     title: "一级-2",
//                 },
//             },
//         },
//     ]
// }