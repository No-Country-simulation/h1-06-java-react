import AccountOptions from "./components/AccountOptions";
import MedicalData from "./components/MedicalData";
import PersonalData from "./components/PersonalData";
import ProfileImage from "./components/ProfileImage";
import "./Profile.css";
import Titles from "../../../components/Titles/Titles";

function Profile() {
  return (
    <div id="profile">
      <Titles title="Mi Perfil" url="/patient/home"></Titles>
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
