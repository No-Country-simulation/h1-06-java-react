/* eslint-disable react/prop-types */
import { Navigate } from "react-router-dom";

export const ProtectedRoutes = ({ children }) => {
  const getUserLocalStorage = localStorage.getItem("user");
  const userLocalStorage = JSON.parse(getUserLocalStorage);
  const userLocal = userLocalStorage;
  if (!userLocal.id) {
    return <Navigate to="/login" />;
  }

  return children;
};
