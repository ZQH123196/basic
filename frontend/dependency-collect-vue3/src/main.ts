import { MyEvent } from "./utils/Event"
import { createApp } from "./utils/index"



const eventList = createApp("#app", {
    data: {
        name: new MyEvent("init name"),
        age: new MyEvent("init age")
    },
})


const nameBtnEl = document.querySelector("#handleNameChange")
const ageBtnEl = document.querySelector("#handleAgeChange")

nameBtnEl?.addEventListener("click", () => {
    eventList.name.value = "name btn click"
})


ageBtnEl?.addEventListener("click", () => {
    eventList.age.value = "age btn click"
})




