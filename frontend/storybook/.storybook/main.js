module.exports = {
  "stories": [
    "../src/**/*.stories.mdx",
    "../src/**/*.stories.@(js|jsx|ts|tsx)"
  ],
  "addons": [
    '@storybook/preset-scss',
    "@storybook/addon-links",
    "@storybook/addon-essentials",
    "@storybook/builder-webpack5"
  ],
  "framework": "@storybook/vue3"
}