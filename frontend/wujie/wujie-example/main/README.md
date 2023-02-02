
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