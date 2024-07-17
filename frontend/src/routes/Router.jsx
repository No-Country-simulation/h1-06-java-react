import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "../pages/login/Login";
import Root from "./root/Root";
import WelcomePage from "../pages/welcome/WelcomePage";
import Welcome from "../pages/welcome/Welcome";
import Register from "../pages/Register/Register";
import PatientHome from "../pages/patient/home/PatientHome";
import Profile from "../pages/patient/profile/Profile";
import EditPersonalData from "../pages/patient/profile/editPersonalData/EditPersonalData";
import Turnos from "../pages/doctor/turnos/Turnos";
import Pacientes from "../pages/doctor/patients/Patients";
import Perfil from "../pages/doctor/perfil/Perfil";
import DoctorHome from "../pages/doctor/home/HomeDoctor";
import Appointment from "../pages/appointment/Appointment";
import { ProtectedRoutes } from "./ProtectedRoutes";

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
        element: <ProtectedRoutes />,
        children: [
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
          {
            path: "/patient/profile/edit-personal-data",
            element: <EditPersonalData />,
          },
          {
            path: "/patient/appointment",
            element: <Appointment />,
          },
          {
            path: "/doctor/home",
            element: <DoctorHome />,
            //errorElement: <ErrorPage />,
          },
          {
            path: "/doctor/turnos",
            element: <Turnos />,
            //errorElement: <ErrorPage />,
          },
          {
            path: "/doctor/pacientes",
            element: <Pacientes />,
            //errorElement: <ErrorPage />,
          },
          {
            path: "/doctor/perfil",
            element: <Perfil />,
            //errorElement: <ErrorPage />,
          },
        ],
      },
    ],
  },
]);

const Router = () => {
  return <RouterProvider router={router} />;
};

export default Router;
