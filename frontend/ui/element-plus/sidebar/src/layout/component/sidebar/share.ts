import { reactive } from "vue";
import { MenuListType } from "./type";

const constantMenu = {
    "name": "Tool",
    "path": "/tool",
    "hidden": false,
    "redirect": "noRedirect",
    "component": "Layout",
    "alwaysShow": true,
    "meta": {
        "title": "系统工具",
        "icon": "tool",
        "noCache": false,
        "link": null
    },
    "children": [
        {
            "name": "Build",
            "path": "build",
            "hidden": false,
            "component": "tool/build/index",
            "meta": {
                "title": "表单构建",
                "icon": "build",
                "noCache": false,
                "link": null
            }
        },
        {
            "name": "Gen",
            "path": "gen",
            "hidden": false,
            "component": "tool/gen/index",
            "meta": {
                "title": "代码生成",
                "icon": "code",
                "noCache": false,
                "link": null
            }
        },
        {
            "name": "Http://localhost:8080/swagger-ui/index.html",
            "path": "http://localhost:8080/swagger-ui/index.html",
            "hidden": false,
            "component": "Layout",
            "meta": {
                "title": "系统接口",
                "icon": "swagger",
                "noCache": false,
                "link": "http://localhost:8080/swagger-ui/index.html"
            }
        }
    ]
}

export const menuList: MenuListType = reactive({ list: [constantMenu] });


