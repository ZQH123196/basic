import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
       vue: 'vue/dist/vue.esm-bundler.js' // 增加这一行 ， 增加动态编译的能力
    }
  },
  base: "./",
  build: {
    outDir: "dist"
  }
})
