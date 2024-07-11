import "./PatientHome.css";
import PatientMenu from "./components/PatientMenu";
import ProfileTitle from "./components/ProfileTitle";

function PatientHome() {
  return (
    <div id="patient-home">
      <ProfileTitle />
      <PatientMenu />
    </div>
  );
}

export default PatientHome;
