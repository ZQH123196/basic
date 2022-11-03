import onregisterSidebar from "./utils/registerSidebar";
import WujieVue from "wujie-vue3";
const { bus } = WujieVue;


bus.$on("registerSidebar", ({preFilter, postHandle}) => {
    onregisterSidebar({}, preFilter, postHandle);
})



