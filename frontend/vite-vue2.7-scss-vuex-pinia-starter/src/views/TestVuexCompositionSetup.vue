<template>
    <div class="bg-white mx-auto">
        <div class="mt-4">
            <div class="text-center">测试 ref</div>
            <div>num: {{ num }}</div>
            <button
                @click="changeNum"
                class="border rounded-4 py-1 px-4 hover:border-blue-700"
            >
                +2
            </button>
        </div>
        <div class="mt-4">
            <div class="text-center">测试 组合式 vue-router</div>
            <div>routePath: {{ route.path }}</div>
            <button
                @click="changeRouter"
                class="border rounded-4 py-1 px-4 hover:border-blue-700"
            >
                跳转到 optionApi
            </button>
        </div>
        <div class="mt-4">
            <div class="text-center">测试 组合式 vuex</div>
            <div>count: {{ count }}</div>
            <button
                @click="changeCount"
                class="border rounded-4 py-1 px-4 hover:border-blue-700"
            >
                +1
            </button>
        </div>
    </div>
</template>

<script setup>
import { ref, computed, getCurrentInstance } from "vue";
import { useStore, useRouter, useRoute } from "../utils/vueApi";


const vm = getCurrentInstance()
console.log("vm", vm)
console.log("vm.proxy.$store", vm.proxy.$store)

const num = ref(1);
function changeNum() {
    num.value += 2;
}

const router = useRouter();
const route = useRoute();
function changeRouter() {
    router.push("/optionApi");
}

const store = useStore();
console.log("store", store);
const count = computed(() => store.state.Counter);
function changeCount() {
    store.commit("setCount", count.value + 1);
}
</script>
