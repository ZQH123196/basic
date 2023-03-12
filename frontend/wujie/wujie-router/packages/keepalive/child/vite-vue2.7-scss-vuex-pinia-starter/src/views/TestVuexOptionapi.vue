<template>
    <div class="bg-white mx-auto">
        <div class="mt-4">
            <div class="text-center">测试 data</div>
            <div>num: {{ num }}</div>
            <button
                @click="changeNum"
                class="border rounded-4 py-1 px-4 hover:border-blue-700"
            >
                +2
            </button>
        </div>
        <div class="mt-4">
            <div class="text-center">测试 选项式 vue-router</div>
            <div>routePath: {{ route.path }}</div>
            <button
                @click="changeRouter"
                class="border rounded-4 py-1 px-4 hover:border-blue-700"
            >
                跳转到 compositionApi
            </button>
        </div>
        <div class="mt-4">
            <div class="text-center">测试 选项式 vuex</div>
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

<script>
export default {
    name: "OptionApi",
    data() {
        return {
            num: 1,
        };
    },
    computed: {
        route() {
            return this.$route;
        },
        count() {
            console.log("this", this);
            console.log("this.$store", this.$store);
            return this.$store.state.Counter.count;
        },
    },
    methods: {
        changeNum() {
            this.num += 2;
        },
        changeRouter() {
            this.$router.push("/compositionApi");
        },
        changeCount() {
            this.$store.Counter.commit("setCount", this.count + 1);
        },
    },
};
</script>
