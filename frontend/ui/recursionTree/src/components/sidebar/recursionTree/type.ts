
// 设计尽量跟 route 相近
// 采用 path 作为 key，path 也是跟 router.getRoutes() 一致的全路径

interface menuType {
    name: string;
    path: string;
    meta: {
        menuProps: {
            title: string;
        };
    };
    children?: menuTypePathEnum[];
}

export type menuTypePathEnum = menuType & {
    // 这个值在递归时使用，是用 路径遍历 组合出最终可访问的 path
    pathEnum?: string;
}