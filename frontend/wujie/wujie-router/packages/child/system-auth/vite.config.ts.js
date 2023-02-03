// vite.config.ts
import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
var vite_config_default = defineConfig({
  server: {
    cors: true,
    host: "0.0.0.0",
    port: 8999
  },
  base: "/SystemAuth/",
  build: {
    outDir: "dist"
  },
  plugins: [vue()]
});
export {
  vite_config_default as default
};
//# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcudHMiXSwKICAic291cmNlc0NvbnRlbnQiOiBbImltcG9ydCB7IGRlZmluZUNvbmZpZyB9IGZyb20gJ3ZpdGUnXHJcbmltcG9ydCB2dWUgZnJvbSAnQHZpdGVqcy9wbHVnaW4tdnVlJ1xyXG5cclxuLy8gaHR0cHM6Ly92aXRlanMuZGV2L2NvbmZpZy9cclxuZXhwb3J0IGRlZmF1bHQgZGVmaW5lQ29uZmlnKHtcclxuICBzZXJ2ZXI6IHtcclxuICAgIGNvcnM6IHRydWUsXHJcbiAgICBob3N0OiAnMC4wLjAuMCcsXHJcbiAgICBwb3J0OiA4OTk5XHJcbiAgfSxcclxuICBiYXNlOiBcIi9TeXN0ZW1BdXRoL1wiLFxyXG4gIGJ1aWxkOiB7XHJcbiAgICBvdXREaXI6IFwiZGlzdFwiXHJcbiAgfSxcclxuICBwbHVnaW5zOiBbdnVlKCldXHJcbn0pIFxyXG4iXSwKICAibWFwcGluZ3MiOiAiO0FBQUE7QUFDQTtBQUdBLElBQU8sc0JBQVEsYUFBYTtBQUFBLEVBQzFCLFFBQVE7QUFBQSxJQUNOLE1BQU07QUFBQSxJQUNOLE1BQU07QUFBQSxJQUNOLE1BQU07QUFBQTtBQUFBLEVBRVIsTUFBTTtBQUFBLEVBQ04sT0FBTztBQUFBLElBQ0wsUUFBUTtBQUFBO0FBQUEsRUFFVixTQUFTLENBQUM7QUFBQTsiLAogICJuYW1lcyI6IFtdCn0K
