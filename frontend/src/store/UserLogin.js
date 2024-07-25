import { create } from "zustand";

export const useUserLogin = create((set) => ({
  user: {},
  patientProfileData: {},
  setUser: (user) => set(() => ({ user })),
  setPatientProfileData: (patientProfileData) =>
    set(() => ({ patientProfileData })),
}));
