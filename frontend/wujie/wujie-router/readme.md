
关于侧边栏，我只需要一个 wujievue 组件，然后动态的通过路由给 wujievue 传值就行了。

比如

```ts
const childRoute: RouteRecordRaw = {
    name: "A",
    path: "A",
    component: wujievue,
    meta: {
        registeName: "",
        registeUrl: ""
    }
}
router.addRoute(childRoute)
```


思路很清晰，子应用注册时提供上述对象，主应用根据这个对象创建两个路由，一个是子应用的根路由，另一个则是子应用的子路由
目的主要是手动处理子应用的子路由，目的是手动将子应用子路由的 path 拦截，然后手动处理，这个在父路由是 hash 路由是时候是必须要做的
代码上更清晰：
```ts

let childAppRegiisterList = [
    {
        mainRouteName: "appA",
        mainRoutePath: "/appA",
        menuText: "appA",
        wujieProps: {
            name: "appA",
            url: "//localhost:9000/",
            exec: true,
            ...lifecycles,
            alive: true,
        },
        childRoute: [""],
    }
];
```


子应用上送的东西将分为几部分处理
1 作为路由注册
2 不作为路由注册，只作为 menuList 生成
3 注册进路由，但是不作为 menuList 生成

有 wujieProps 的注册进路由，并生成 :path 的子路由
没有 wujieProps 不能注册进路由，


有 menuProps 的生成 menuList


