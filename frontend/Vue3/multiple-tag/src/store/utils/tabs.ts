import { defineStore } from 'pinia';



export interface ITabsItem {
    name: string; // 路由名
    path: string; // 路径
    activePath?: string; // 激活的路由（用于左侧菜单样式）
    title: string; // 路由中文名
    query?: {
        [key: string]: any;
    };
    params?: {
        [key: string]: any;
    };
}

type ITabs = {
    tabs: ITabsItem[]
}

const HomePageState: ITabsItem = {
    name: 'home',
    path: '/home',
    title: '首页'
}

const HomePageStateJson = JSON.stringify(HomePageState);

const str_tabs = "tabs"

export const useTabsStore = defineStore({
    id: "tabs",
    state: (): ITabs => ({
        tabs: JSON.parse(window.sessionStorage.getItem(str_tabs) ?? HomePageStateJson)
    }),
    actions: {
        setStorage(tabs: ITabs) {
            window.sessionStorage.setItem(str_tabs, JSON.stringify(tabs))
        }
    }
})