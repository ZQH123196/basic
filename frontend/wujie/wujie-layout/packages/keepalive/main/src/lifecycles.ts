export const lifecycles = {
    beforeLoad: (appWindow) => console.log(`beforeLoad 子应用生命周期激活, wujieChildAppId = [${appWindow.__WUJIE.id}]`),
    beforeMount: (appWindow) => console.log(`beforeMount 子应用生命周期激活, wujieChildAppId = [${appWindow.__WUJIE.id}]`),
    afterMount: (appWindow) => console.log(`afterMount 子应用生命周期激活, wujieChildAppId = [${appWindow.__WUJIE.id}]`),
    beforeUnmount: (appWindow) => console.log(`beforeUnmount子应用生命周期激活, wujieChildAppId = [${appWindow.__WUJIE.id}]`),
    afterUnmount: (appWindow) => console.log(`afterUnmount 子应用生命周期激活, wujieChildAppId = [${appWindow.__WUJIE.id}]`),
    activated: (appWindow) => console.log(`activated 子应用生命周期激活, wujieChildAppId = [${appWindow.__WUJIE.id}]`),
    deactivated: (appWindow) => console.log(`deactivated 子应用生命周期激活, wujieChildAppId = [${appWindow.__WUJIE.id}]`),
    loadError: (url, e) => console.log(`${url} 加载失败`, e),
};
