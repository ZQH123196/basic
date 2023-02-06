import { reactive } from "vue";


type sideBarMenuListType = {
    to: string;
    menuText: string | symbol | undefined;
    order?: number;
}[]


const constantMenuList = []
let sideBarMenuList: sideBarMenuListType = reactive(constantMenuList);

export { sideBarMenuList }
export type {sideBarMenuListType}