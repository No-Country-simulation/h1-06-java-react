import { Outlet, ScrollRestoration } from "react-router-dom";
import NavBar from "../../components/NavBar/NavBar";

function Root() {
  return (
    <div>
      <Outlet />
      <ScrollRestoration />
      <NavBar />
    </div>
  );
}

export default Root;
