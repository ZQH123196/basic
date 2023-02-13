import { reactive } from "vue";
import { MenuListType } from "./type";

const constantMenu = [
    {
        "name": "Tool",
        "path": "/tool",
        "redirect": "noRedirect",
        "component": "Layout",
        "alwaysShow": true,
        "meta": {
            "title": "系统工具",
            "icon": "tool",
        },
        "children": [
            {
                "name": "Build",
                "path": "build",
                "component": "tool/build/index",
                "meta": {
                    "title": "表单构建",
                    "icon": "build",
                }
            },
            {
                "name": "Http://localhost:8080/swagger-ui/index.html",
                "path": "http://localhost:8080/swagger-ui/index.html",
                "component": "Layout",
                "meta": {
                    "title": "系统接口",
                    "icon": "swagger",
                    "link": "http://localhost:8080/swagger-ui/index.html"
                },
                "children": [
                    {
                        "name": "xx1",
                        "path": "xx1",
                        "component": "tool/xx1/index",
                        "meta": {
                            "title": "接口一",
                            "icon": "xx1",
                        }
                    },
                ]
            }
        ]
    }
]
export const menuList = reactive({ list: [...constantMenu] });


