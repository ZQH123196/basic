import { ref, watch } from "vue";
import { onBeforeRouteLeave } from "vue-router";
import router from "./router";


/**
 * watch 相当于注册 cb 函数，也就是 listener，js 的实现是通过 set 收集 listener，get involve listener
 * 所以第一个入参必须返回一个被代理的对象
 */
// let obj = ref("123")
// watch(obj, (nVal: any, oVal: any) => {
//     console.log(`watch nVal = [${nVal}]`);
//     console.log(`watch oVal = [${oVal}]`);
// })
// obj.value = "456"

function watchRoutes() {
    // watch(() => window.route, (nVal: any, oVal: any) => {
    //     console.log(`watch nVal = [${nVal}]`);
    //     console.log(`watch oVal = [${oVal}]`);
    // }, { deep: true })

}


export {
    watchRoutes,
}