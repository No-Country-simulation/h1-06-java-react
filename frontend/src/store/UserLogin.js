import { create } from "zustand";

export const useUserLogin = create((set) => ({
  user: {},
  setUser: (user) => set(() => ({ user })),
}));
