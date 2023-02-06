

export const routeModeHistory = "history"
export const routeModeHash = "hash"

export let initAppA: childAppOpsType = {
    routeProps: {
        name: "appA",
        path: "/appA",
        meta: {
            routeMode: "history",
        }
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
        meta: {
            routeMode: "hash",
        }
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