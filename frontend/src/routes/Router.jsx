import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "../pages/login/Login";
import Root from "./root/Root";
import WelcomePage from "../pages/welcome/WelcomePage";
import Welcome from "../pages/welcome/Welcome";
import Register from "../pages/Register/Register";
import PatientHome from "../pages/patient/home/PatientHome";
import Profile from "../pages/patient/profile/Profile";

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
      {
        path: "/register",
        element: <Register />,
        //errorElement: <ErrorPage />,
      },
      {
        path: "/patient/home",
        element: <PatientHome />,
        //errorElement: <ErrorPage />,
      },
      {
        path: "/patient/profile",
        element: <Profile />,
        //errorElement: <ErrorPage />,
      },
    ],
  },
]);

const Router = () => {
  return <RouterProvider router={router} />;
};

export default Router;
