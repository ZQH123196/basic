/**
 * https://www.bilibili.com/video/BV1jr4y1H7e4/?spm_id_from=333.788.recommend_more_video.24&vd_source=a68b399d6e8e5b548bb0c0266470dbae
 * 完全掌控 url：阻止 a 标签的默认行为，使用 history api
 * 可控跳转与可控渲染：通过 fetch 来直接获取后台 html，然后插入节点的 innerHtml 中去。可控渲染就是在插入之前，可以对获取的 html 做任意的加工，比如获取空白就统一返回前端规定的 404。
 * 接管浏览器前进后退：重写 popstatus
 * 
 */

/**
 *  history api 使用
 * 一个体现是，我直接输入 url，应当被重定向回 index.html，因为定义路由的代码在里面，因此需要开发跟生产环境都设置重定向。
 */



/**
 * 实现了 url 跳转但是不发生页面变更，避免丢失页面状态
 */
document.querySelectorAll("#app>nav>#routeLink")?.forEach((el) => {
    el.addEventListener("click", (e) => {
        e.preventDefault()
        useRouter(e.target as HTMLLinkElement)
    })
})



const routeViewEl = document.querySelector("#routeView")
function useRouter(aEl: HTMLLinkElement) {
    const to = aEl.href
    history.pushState({ from: location.pathname, to }, "", to)
    renderPage(to)
}

async function renderPage(url: URL | RequestInfo = location.hostname) {
    console.log(url);
    
    const targetHtml = await(await fetch(url)).text()
    routeViewEl!.innerHTML = targetHtml!
}
// http://127.0.0.1:5173/src/pages/page4.html

window.onpopstate = renderPage

// todo 使用 location.pathname 作为 path（key）去取对应的真实 url