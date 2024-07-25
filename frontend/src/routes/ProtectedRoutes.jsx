/* eslint-disable react/prop-types */
import { useEffect } from "react";
import { useUserLogin } from "../store/UserLogin";

export const ProtectedRoutes = ({ children }) => {
  const { user } = useUserLogin();
  const getUserLocalStorage = localStorage.getItem("user");
  const userLocalStorage = JSON.parse(getUserLocalStorage);
  const userLocal = userLocalStorage;

  useEffect(() => {
    if (!userLocal?.id && !user?.id) {
      window.location.href = "/login";
    }
  }, [user, userLocal]);

  return children;
};
