import { Outlet, ScrollRestoration } from "react-router-dom";

function Root() {
  return (
    <div>
      <Outlet />
      <ScrollRestoration />
    </div>
  );
}

export default Root;
