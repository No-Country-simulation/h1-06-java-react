import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  base: "/",
  plugins: [react()],
  preview: {
    host: true,
    port: 8090,
    strictPort: true,
  },
  server: {
    host: true,
    port: 8090,
    strictPort: true,
  },
});