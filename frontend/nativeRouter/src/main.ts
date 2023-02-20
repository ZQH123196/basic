



/**
 * 先关闭点击 a 标签的自动行为，避免跳转
 * 期望使用 history api 来对 url 进行操控
 */


document.querySelector("#app>nav")?.addEventListener("click", (e) => {
    console.log(e.target);
    
    // @ts-ignore
    if (e.target.tagName == "A") {
        // 阻止 a 标签默认行为，避免发生真的跳转
        e.preventDefault();
        const aEl = e.target as HTMLLinkElement
        const toUrl = aEl.href
        // 手动控制 url 变更
        history.pushState({page: aEl.textContent}, "", toUrl);
    }
})



