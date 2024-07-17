import { Outlet, ScrollRestoration } from "react-router-dom";
import NavBar from "../../components/NavBar/NavBar";
import NavbarDoctor from "../../components/NavBar/NavbarDoctor";
import { useState } from "react";

function Root() {
  const [role, setRole] = useState(undefined);

  return (
    <div>
      <Outlet />
      <ScrollRestoration />
      {role === "doctor" ? (
        <NavbarDoctor />
      ) : role === "patient" ? (
        <NavBar />
      ) : null}
    </div>
  );
}

export default Root;
