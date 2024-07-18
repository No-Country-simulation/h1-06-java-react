import { Outlet, ScrollRestoration } from "react-router-dom";
import NavBar from "../../components/NavBar/NavBar";
import NavbarDoctor from "../../components/NavBar/NavbarDoctor";
import { useUserLogin } from "../../store/UserLogin";

function Root() {
  const { user } = useUserLogin();

  return (
    <div>
      <Outlet />
      <ScrollRestoration />
      {user.role === "[ROLE_DOCTOR]" ? (
        <NavbarDoctor />
      ) : user.role === "[ROLE_PACIENTE]" ? (
        <NavBar />
      ) : null}
    </div>
  );
}

export default Root;
