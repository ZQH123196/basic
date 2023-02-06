# Vue 3 + TypeScript + Vite

这个并没有监听 routes，而是在修改路由时，触发 menulist 的修改，从而更新路由。
但是这个只是下级做法，好一点的还是要监听 routes 的变更，将自己的变更函数注册成其依赖。