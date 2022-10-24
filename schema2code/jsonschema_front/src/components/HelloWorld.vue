<script setup lang="ts">
import { ref, watch, watchEffect } from 'vue'
import axios from 'axios';
import { TheRootSchema, TheUserSchema } from '@/autoType/userSchema';

defineProps<{ msg: string }>()

const count = ref(0)


const userRoot: TheRootSchema = {
  "user": {
    "id": 1,
    "name": "用户一",
    "age": 8999,
    "tags": [
      "是个人类",
      "也许是个人类",
      1,
      1.1
    ]
  }
}

// const user: TheUserSchema = {
//   "id": 1,
//   "name": "用户一",
//   "age": 8999,
//   "tags": [
//     "是个人类",
//     "也许是个人类",
//     1,
//     1.1
//   ]
// }



// async setup()
let res: TheUserSchema = ref() as any;

const getData = async () => {
  await axios.post("http://localhost:8080/echo", userRoot).then((response) => {
    const userRoot: TheRootSchema = response.data;
    res.value = userRoot.user
  });
}


</script>

<template>

  <button type="button" @click="getData">getData</button>
  <br />
  {{ res }}
  <br />
  <br />
  <hr />
  userId = {{ res?.id }}
  <hr />
  userName = {{ res?.name }}
  <hr />
  userTags = {{ res?.tags }}
  <hr />

</template>

<style scoped>
</style>
