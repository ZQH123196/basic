import { RouteRecordRaw } from "vue-router";
import { MenuItemType } from "./type";
import getRemoteRoutes from "@/api/getRemoteRoutes";


/**
 * simpleRouteList 指没有 children 的
 * nestedMenuList 指有 children
 * 
 * 其实 vue route 已经自动将嵌套的路由给 flatten 了，打印一下 router.getRoutes() 便知
 * 所以可以省略嵌套路由的转换，直接将 simpleRouteList 给显示就行
 * @param curRoutes
 */
export function mapRoutes2MenuList(curRoutes: RouteRecordRaw[]) {
    let flattenRouteList = curRoutes.filter((route) => route.children && route.children.length == 0);
    let menuList = flattenRouteList.map(routeItem2Menu);
    return menuList;
}



export function routeItem2Menu(simpleRoute: RouteRecordRaw): MenuItemType {
    return {
        path: simpleRoute.path,
        order: simpleRoute.meta?.order as number,
        menuText: simpleRoute.meta?.menuText as string,
    };
}