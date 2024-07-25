import profileImage from "../../../../../public/assets/images/profile.png";
import { useUserLogin } from "../../../../store/UserLogin";
import checkLocalTime from "../../../../hooks/checkLocalTime";
function ProfileTitle() {
  const { user } = useUserLogin();
  return (
    <div id="profile-title">
      <div id="profile-title-container">
        <div id="profile-image">
          <img src={profileImage} />
        </div>
        <div id="profile-title">
          <span className="flex-row">
            Hola {user.name} {checkLocalTime()}
          </span>
        </div>
      </div>
    </div>
  );
}

export default ProfileTitle;
