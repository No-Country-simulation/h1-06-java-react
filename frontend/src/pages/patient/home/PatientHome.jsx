import "./PatientHome.css";
import PatientMenu from "./components/PatientMenu";
import PatientNotifications from "./components/PatientNotifications";
import ProfileTitle from "./components/ProfileTitle";
import SettingsIcon from "../../../../public/assets/icons/settings.svg";
import { Link } from "react-router-dom";

function PatientHome() {
  return (
    <div id="patient-home">
      <div id="patient-home-header" className="flex-row justify-between">
        <div></div>
        <Link to="/patient/profile">
          <img src={SettingsIcon} />
        </Link>
      </div>
      <ProfileTitle />
      <PatientMenu />
      <PatientNotifications />
    </div>
  );
}

export default PatientHome;
