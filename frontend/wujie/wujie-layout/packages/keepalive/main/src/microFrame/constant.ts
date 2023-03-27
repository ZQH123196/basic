import { childAppOpsType, credentialsFetch, registerMenu, registerVueInstance } from "./setupChildApp"

const degrade = window.localStorage.getItem("degrade") === "true" || !window.Proxy || !window.CustomElementRegistry;
export const routeModeHistory = "history"
export const routeModeHash = "hash"

let initAppA: childAppOpsType = {
    routeProps: {
        name: "appA",
        path: "/appA",
    },
    wujieProps: {
        name: "appA",
        url: "//localhost:9000/",
        props: { registerMenu, registerVueInstance },
        alive: true,
        exec: true,
        fetch: credentialsFetch,
        degrade,
    }
}

let initAppB: childAppOpsType = {
    routeProps: {
        name: "appB",
        path: "/appB",
    },
    wujieProps: {
        name: "appB",
        url: "//localhost:9001/",
        props: { registerMenu, registerVueInstance },
        alive: true,
        exec: true,
        fetch: credentialsFetch,
        degrade,
    }
}


let vite_vue27_ts: childAppOpsType = {
    routeProps: {
        name: "vite_vue27_ts",
        path: "/vite_vue27_ts",
    },
    wujieProps: {
        name: "vite_vue27_ts",
        url: "//localhost:9002/",
        props: { appName: "vite_vue27_ts", registerMenu, registerVueInstance },
        alive: true,
        exec: true,
        fetch: credentialsFetch,
        degrade,
    }
}

export const initApps = [initAppA, initAppB, vite_vue27_ts]