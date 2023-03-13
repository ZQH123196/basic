<script lang="ts" setup>
import axios from "axios";
import { getCurrentInstance, onBeforeMount, onBeforeUnmount, onMounted, ref } from "vue";
axios.post
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

