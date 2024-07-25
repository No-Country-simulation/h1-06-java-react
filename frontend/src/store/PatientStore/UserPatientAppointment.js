import { create } from "zustand";

export const useUserPatientAppointment = create((set) => ({
  appointment: {},
  setAppointment: (appointment) => set(() => ({ appointment })),
}));
