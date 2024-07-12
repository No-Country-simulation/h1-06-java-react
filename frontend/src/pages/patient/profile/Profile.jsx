import AccountOptions from "./components/AccountOptions";
import MedicalData from "./components/MedicalData";
import PersonalData from "./components/PersonalData";
import ProfileImage from "./components/ProfileImage";
import "./Profile.css";

function Profile() {
  return (
    <div id="profile">
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
