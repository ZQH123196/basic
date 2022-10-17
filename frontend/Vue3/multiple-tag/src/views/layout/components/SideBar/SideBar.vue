<script setup lang="ts">
import { defineComponent, h, ref, render } from 'vue'
import SideBarIcon from './components/SideBarIcon.vue';

const className: string = "siderbar-btn"

debugger

const Deliver = defineComponent({
    render() {
        return h(
            "div",
            {
                class: "border mx-1"
            },
            undefined
        )
    }
})
</script>

<template>
    <div class="h-screen fixed top-0 left-0 w-16 flex flex-col dark:bg-gray-900 bg-white shadow-lg">
        <SideBarIcon text="0" :class="className" />
        <SideBarIcon text="1" :class="className" />
        <Deliver />
        <SideBarIcon text="4" :class="className" />
        <SideBarIcon text="2" :class="className" />
        <SideBarIcon text="3" :class="className" />
        <Deliver />
        <SideBarIcon text="4" :class="className" />
        <SideBarIcon text="4" :class="className" />
    </div>
</template>

