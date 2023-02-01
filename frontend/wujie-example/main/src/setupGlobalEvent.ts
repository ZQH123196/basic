import onregisterSidebar from "./utils/registerSidebar";
import WujieVue from "wujie-vue3";
const { bus } = WujieVue;


bus.$on("registerSidebar", ({routeMap, preFilter, postHandle}) => {
    onregisterSidebar(routeMap, preFilter, postHandle);
})



