import { ComponentOptionsMixin, DefineComponent, reactive } from "vue";
import { userType } from "../user/type";



const user: userType = undefined!

const userProxy = reactive({
    user
});


export {
    userProxy,
}

