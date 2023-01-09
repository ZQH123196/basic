import reactive from "./utils/reactive"
import { watchEffect } from "./utils/watchEffect"

const obj: any = reactive({
    a: 1,
    b: {
        c: 3
    }
})


const domA = document.querySelector("#a")
const domAPlus = document.querySelector("#aPlus")
const domC = document.querySelector("#c")

domA?.addEventListener("click", () => {
    obj.a = 10;
    console.log(obj.a);
}, false);
domAPlus?.addEventListener("click", () => {
    obj.a += 1;
    console.log(obj.b.c);
}, false);



domC?.addEventListener("click", () => {
    obj.b.c = 11;
    console.log(obj.b.c);
}, false);




watchEffect(() => {
    console.log("watchEffect obj.a", obj.a);
})

watchEffect(() => {
    console.log("watchEffect obj.b.c", obj.b.c);
})








