import "./PatientHome.css";
import PatientMenu from "./components/PatientMenu";
import PatientNotifications from "./components/PatientNotifications";
import ProfileTitle from "./components/ProfileTitle";

function PatientHome() {
  return (
    <div id="patient-home">
      <ProfileTitle />
      <PatientMenu />
      <PatientNotifications />
    </div>
  );
}

export default PatientHome;
