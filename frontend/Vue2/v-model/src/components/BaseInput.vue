<template>
  <div>
    <input type="text" @input="handleInput"/>    
  </div>
</template>

<script>
export default {
    // v-model 默认就是绑定 :value 同时监听 @input 事件的 -> :value="xxx" @input="xxx = $event"
    // 这里的 value,是父容器 :value="xxx" 单向数据绑定的值
    props: ["value"],
    methods: {
        handleInput(e) {
            // 注意这里的 input 本质上指的是父容器上所监听的事件
            // 这里是 input，父容器就要监听 @input
            // 然后子容器使用 $emit 发出这个事件让监听的父容器更新值
            this.$emit('input', e.target.value)
        }
    }

}
</script>

