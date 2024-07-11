import profileImage from "../../../../../public/assets/images/profile.png";
function ProfileTitle() {
  return (
    <div id="profile-title">
      <div id="profile-title-container">
        <div id="profile-image">
          <img src={profileImage} />
        </div>
        <div id="profile-title">Hola {"user"} Buenos dias</div>
      </div>
    </div>
  );
}

export default ProfileTitle;
