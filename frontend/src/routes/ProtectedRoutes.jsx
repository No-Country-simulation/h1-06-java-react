import { useState } from "react";
import { Navigate } from "react-router-dom";
/*import { useAuth } from "../context/AuthProvider";*/

export const ProtectedRoutes = ({ children }) => {
  const [user, setUser] = useState(undefined);
  // const { user } = useAuth();

  if (!user) {
    return <Navigate to="/login" />;
  }

  return children;
};
