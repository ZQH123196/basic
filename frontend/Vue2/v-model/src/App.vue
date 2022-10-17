<template>
  <div id="app">
    <BaseInput v-model="message"/>
    <BaseInput :value="message" @input="message = $event"/>
    <p>父容器显示的值：{{message}}</p>

    <BaseCheckbox v-model="checked"/>
    <!-- 这个时候就会有问题，因为原生 checkbox 他设置 勾选的是 checked 属性为 true，而不是 input，所以这里绑定 input 不会有用 -->
    <!-- <BaseCheckbox :value="checked" @input="checked = $event"/> -->
    <BaseCheckbox :checked="checked" @input="checked = $event"/>

    <h1>表单输入绑定</h1>
    <p>输入换行问题，css 渲染换行需要使用 style="white-space: pre-line;"</p>
    <textarea v-model="text"></textarea>
    <p >输入的值不能换行：{{text}}</p>
    <p style="white-space: pre-line;">输入的值可以换行：{{text}}</p>


    <h1>选中加入</h1>
    <p>要将多个选择加入时，多个 input(type="checkbox") 应该 v-model 绑定同一个值</p>
    <label for="a">a:<input id="a" type="checkbox" value="a" v-model="checkedNames"/> </label>
    <label for="b">b:<input id="b" type="checkbox" value="b" v-model="checkedNames"/> </label>
    <label for="c">c:<input id="c" type="checkbox" value="c" v-model="checkedNames"/> </label>

    <p>选中的名字：{{ checkedNames }}</p>


    <h1>单选</h1>
    <label for="choseA">choseA:<input id="choseA" type="radio" value="choseA" v-model="picked" /> </label>
    <label for="choseB">choseB:<input id="choseB" type="radio" value="choseB" v-model="picked" /> </label>
    <p>{{picked}}</p>

    <h1>select 的单选和多选</h1>
    <h2>单选</h2>
    <select v-model="selected">
      <option disabled value="">请选择</option>
      <option value="a">a</option>
      <option value="b">b</option>
      <option value="c">c</option>
    </select>
    <p>{{selected}}</p>

    <h2>多选，按住 shift 或 ctrl 进行多选</h2>
    <select v-model="selectedMultiple" multiple>
      <option disabled value="">请选择</option>
      <option value="a">a</option>
      <option value="b">b</option>
      <option value="c">c</option>
    </select>
    <p>{{selectedMultiple}}</p>

    <br/>
    <br/>
    <br/>
    <br/>
  </div>
</template>

<script>
import BaseInput from '@/components/BaseInput.vue';
import BaseCheckbox from '@/components/BaseCheckbox.vue';


export default {
  name: 'App',
  components: {
    BaseInput,
    BaseCheckbox
  },
  data: function() {
    return {
      message: "BaseInput 的值",
      checked: true,
      text: "输入值然后换行",
      checkedNames: [],
      picked: "",
      selected: "",
      selectedMultiple: []
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
