export interface userType {
    info: {
        name: string;
        age: string;
    };
    permissionRoutes?: {
        path: string;
        component: any;
        meta: {
            order: number;
            menuText: string;
        };
    }[];
}