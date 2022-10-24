import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import themePreprocessorPlugin from "@zougt/vite-plugin-theme-preprocessor"
import path from 'path';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    themePreprocessorPlugin({
      less: {
        // 各个主题文件的位置
        multipleScopeVars: [
          {
            scopeName: "theme-default",
            path: path.resolve("src/theme/default.less"),
          },
          {
            scopeName: "theme-green",
            path: path.resolve("src/theme/green.less"),
          },
        ],
      },
    }),]
})
