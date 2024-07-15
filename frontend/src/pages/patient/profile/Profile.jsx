import AccountOptions from "./components/AccountOptions";
import MedicalData from "./components/MedicalData";
import PersonalData from "./components/PersonalData";
import ProfileImage from "./components/ProfileImage";
import "./Profile.css";
import chevronLeft from "../../../../public/assets/icons/chevronLeft.svg";
import { Link } from "react-router-dom";

function Profile() {
  return (
    <div id="profile">
      <div className="profile-header">
        <Link to="/patient/home" className="profile-header-back">
          <img src={chevronLeft} />
        </Link>
        <div>
          <h2>Ajustes</h2>
        </div>
        <div></div>
      </div>
      <div id="profile-container">
        <ProfileImage />
        <PersonalData />
        <hr className="profile-hr" />
        <MedicalData />
        <hr className="profile-hr" />
        <AccountOptions />
      </div>
    </div>
  );
}

export default Profile;
