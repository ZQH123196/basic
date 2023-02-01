import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    cors: true,
    host: '0.0.0.0',
    port: 8999
  },
  base: "/SystemAuth/",
  build: {
    outDir: "dist"
  },
  plugins: [vue()]
}) 
