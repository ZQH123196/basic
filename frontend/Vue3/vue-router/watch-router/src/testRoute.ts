
import router from "./router";
import { watchRoutes } from "./router/registerRouteDep";


console.log("router: ", router);
console.log("routes: ", router.getRoutes());
console.log("currentRoute: ", router.currentRoute.value);

setTimeout(() => {
    watchRoutes();
    router.removeRoute("One");
}, 2000);



setTimeout(() => {
    console.log("routes: ", router.getRoutes());
    console.log("router: ", router);

}, 3000);



export {

}

