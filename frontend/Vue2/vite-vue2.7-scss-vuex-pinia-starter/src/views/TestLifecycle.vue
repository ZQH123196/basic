<script lang="ts" setup>
import { getCurrentInstance, onBeforeMount, onBeforeUnmount, onMounted } from "vue";

/**
 * TestLifecycle.vue:5 onBeforeMount！
 * TestLifecycle.vue:8 onMounted！
 * TestLifecycle.vue:13 onBeforeUnmount！
 */
onBeforeMount(() => {
    console.log("onBeforeMount！");
});
onMounted(() => {
    console.log("onMounted！");
    const instance = getCurrentInstance()?.proxy
    instance?.$destroy()
});
onBeforeUnmount(() => {
    console.log("onBeforeUnmount！");
});
</script>

