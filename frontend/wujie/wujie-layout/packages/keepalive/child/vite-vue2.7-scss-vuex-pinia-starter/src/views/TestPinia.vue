<template>
    <div class="container">
        <!-- <img src='../assets/vue.svg'/>
    <img src='../assets/vite.svg'/> -->
        <a href="https://vitejs.dev" target="_blank">
            <img src="../assets/vite.svg" class="logo" alt="Vite logo" />
        </a>
        <a href="https://vuejs.org/" target="_blank">
            <img src="../assets/vue.svg" class="logo vue" alt="Vue logo" />
        </a>
        <h1>Vite + VUE2</h1>
        <h2>
            Vite ⚡ - Vue2 - TS - VueRouter - Vuex - Axios - Sass / Starter
            Template
        </h2>
        <!-- <h4>{{ count }}</h4> -->
        <h4>{{ exampleStore.counter }}</h4>
        <button @click="add">Count++</button>
    </div>
</template>

<!-- setup 风格 -->
<script lang="ts" setup >
import { ref } from "vue";
import { piniaStore } from "../store";

const exampleStore = piniaStore.useStore();

function add() {
    exampleStore.counter++
}


</script>


<!-- defineComponent 中的 setup -->
<!-- <script lang="ts">
import { defineComponent } from "vue";
import { piniaStore } from "../store";


export default defineComponent({
    setup(props) {
        const exampleStore = piniaStore.useStore();
        return {
            // 您可以返回整个 store 实例以在模板中使用它
            exampleStore,
        };
    },
    methods: {
        add() {
            this.exampleStore.counter++;
        },
    },
});
</script> -->




<style lang="scss" scoped>
.container {
    text-align: center;
}
.logo {
    height: 6em;
    padding: 1.5em;
    will-change: filter;
}
.logo:hover {
    filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
    filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
