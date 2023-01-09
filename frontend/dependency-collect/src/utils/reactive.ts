import { listenerFn } from "./watchEffect";

const listenerKeyMapList: {
    [_: string | symbol]: Set<Function>
} = {}

function registerListener(key: string | symbol, listenerFn: Function | null) {
    if (listenerFn != null) {
        if (!listenerKeyMapList[key]) {
            listenerKeyMapList[key] = new Set<Function>();
        }
        let listenerSet = listenerKeyMapList[key];
        listenerSet.add(listenerFn);
    }
}

function notify(target: Object, key: string | symbol, newValue: any, receiver: any) {
    const listenerSet = listenerKeyMapList[key];
    listenerSet.forEach((cbFn) => {
        cbFn();
    })
}

export default function reactive<T extends Object>(obj: T) {
    return new Proxy(obj, {
        get(target: T, key: string | symbol, receiver: any) {
            console.log("get", key);
            // 注册监听者
            registerListener(key, listenerFn);
            
            const value = Reflect.get(target, key, receiver);
            if (typeof value === "object" && value != null) {
                reactive(value)
            }
            return value;
        },
        set(target: T, key: string | symbol, newValue: any, receiver: any): boolean {
            console.log("set", key);
            // 通知所有监听者
            notify(target, key, newValue, receiver);
            return Reflect.set(target, key, newValue, receiver);
        }
    })


}