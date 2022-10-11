import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 3000,
    proxy: {
      '*': {
        target: 'http://localhost:8080/echo',
        changeOrigin: true
      }
    }
  }
})