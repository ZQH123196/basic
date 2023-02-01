export interface MenuListType {
    list: MenuItemType[];
}

export interface MenuItemType {
    path: string;
    order: number;
    menuText: string;
}