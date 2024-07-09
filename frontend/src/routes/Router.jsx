import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "../pages/login/Login";
import Root from "./root/Root";
import WelcomePage from "../pages/welcome/WelcomePage";
import Welcome from "../pages/welcome/Welcome";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    children: [
      {
        path: "/",
        element: <WelcomePage />,
        //errorElement: <ErrorPage />,
      },
      {
        path: "/welcome",
        element: <Welcome />,
        //errorElement: <ErrorPage />,
      },
      {
        path: "/login",
        element: <Login />,
        //errorElement: <ErrorPage />,
      },
    ],
  },
]);

const Router = () => {
  return <RouterProvider router={router} />;
};

export default Router;
