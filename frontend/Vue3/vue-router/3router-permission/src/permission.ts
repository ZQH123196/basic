import { NavigationGuardNext, RouteLocationNormalized, Router } from "vue-router";
import getRemoteRoutes from "./api/getRemoteRoutes";
import { menuList } from "./layout/component/sidebar/share";
import { mapRoutes2MenuList } from "./layout/component/sidebar/utils";
import { userProxy } from "./store";
import Four from "@/layout/component/sidebar/child/Four.vue";

const whiteList = ['/login', '/register'];

/**
 * 注册路由守卫
 * @param router 
 */
function setupPermission(router: Router) {
    router.beforeEach((to: RouteLocationNormalized, from: RouteLocationNormalized, next: NavigationGuardNext) => {
        if (testLogin()) {
            console.log(`已经登陆，放行，从 [${from}] 前往 [${to}]`);
            next()
        } else {
            console.log(`登陆失败，userProxy.user = ${userProxy.user}，接下来进入登陆逻辑！`);

            toLogin(router)
            next({ path: "/",replace: true })
        }

    })
}

/**
 * 验证是否登录，方式很多
 * 从 store 拿
 * 直接从 login 拿
 * @returns 
 */
function testLogin(): boolean {
    if (userProxy.user) {
        return true
    }

    return false;
}


function login() {
    const user = {
        info: {
            name: "eor",
            age: "8999"
        },
        permissionRoutes: [
            {
                path: "/Four",
                component: Four,
                meta: {
                    order: 4,
                    menuText: "Navigator Four",
                },
            },
        ]
    };
    userProxy.user = user;
}

async function toLogin(router: Router) {
    // 正常登陆是要到 login 登录页去做的，这里直接赋值放过
    await login();
    console.log(`登陆成功！userProxy.user = `, userProxy.user);

    const remoteRouteget = userProxy.user.permissionRoutes;
    remoteRouteget?.forEach((route: RouteLocationNormalized) => {
        router.addRoute(route)
    })

    // 变更 menuList.list 来触发菜单的响应式更新 
    menuList.list = mapRoutes2MenuList(router.getRoutes());
}




export default setupPermission;