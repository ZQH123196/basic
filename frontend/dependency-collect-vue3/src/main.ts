import { ref } from "./utils/hooks"
import { createApp } from "./utils/index"



createApp("App", {
    refs: {
        name: ref("init name"),
        age: ref("init age")
    },
    methods: {
        setName(this: any) {
            this.name.value = "eor"
        }
    }
})







