# Vue 3 + Typescript + Vite




### storybook
storybook 初始化：
pnpm dlx sb init

scss 依赖，@storybook/preset-scss 是 storybook 的插件
pnpm i @storybook/preset-scss css-loader sass sass-loader style-loader -D

然后在文件 .storybook/main.js 添加这个插件
module.exports = {
  addons: ['@storybook/preset-scss'],
};