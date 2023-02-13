import { childAppOpsType, credentialsFetch, degrade, registerMenu, registerVueInstance } from "./setupChildApp"

const degrade = window.localStorage.getItem("degrade") === "true" || !window.Proxy || !window.CustomElementRegistry;
export const routeModeHistory = "history"
export const routeModeHash = "hash"

export let initAppA: childAppOpsType = {
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

export let initAppB: childAppOpsType = {
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