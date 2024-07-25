/* eslint-disable no-undef */
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
import Appointment from "../pages/patient/schedule/appointment/Appointment";
import { ProtectedRoutes } from "./ProtectedRoutes";
import { useUserLogin } from "../store/UserLogin";
import HistoryPatient from "../pages/doctor/historyPatient/HistoryPatient";
import Cancel from "../pages/doctor/turnos/components/Cancel";
import ResultPatient from "../pages/doctor/patients/ResultPatient/ResultPatient";
import MedicalHistory from "../pages/patient/medicalHistory/MedicalHistory";
import PasswordRecovery from "../pages/PasswordRecovery/PasswordRecovery";

const Router = () => {
  const { user } = useUserLogin();

  console.log("userrr:", user);

  const router = createBrowserRouter([
    {
      element: (
        <ProtectedRoutes user={user}>
          <Root />
        </ProtectedRoutes>
      ),
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
          path: "/patient/medical-history",
          element: <MedicalHistory />,
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
        {
          path: "/doctor/historialPaciente",
          element: <HistoryPatient />,
          //errorElement: <ErrorPage />,
        },
        {
          path: "/doctor/turnos/cancel",
          element: <Cancel />,
          //errorElement: <ErrorPage />,
        },
      ],
    },
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
      path: "/password-recovery",
      element: <PasswordRecovery />,
    },
  ]);

  return <RouterProvider router={router} />;
};

export default Router;
