import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue2'
import viteCompression from 'vite-plugin-compression';
import path from 'path';

const HOST = "0.0.0.0"
const REPLACEMENT = `${path.resolve(__dirname, './src')}/`

export default (/** if you want to use mode : { mode }*/) => {
  return defineConfig({
    base: "./",
    server: {
      host: HOST,
      port: 9002,
    },
    resolve: {
      alias: [
        {
          find: '@/',
          replacement: REPLACEMENT,
        },
        {
          find: 'src/',
          replacement: REPLACEMENT,
        },
      ],
    },
    plugins: [
      vue(),
      viteCompression()
    ],
  })
}


