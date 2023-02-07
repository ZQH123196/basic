export interface MenuListType {
    list: menuListItemType[];
}

export interface menuListItemType {
    order: number;
    menuText: string;
    path: string;
}