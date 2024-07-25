import ProfilePhoto from '/public/assets/images/profile.png'
import CameraIcon from '/public/assets/icons/camera.svg'

function ProfileImage() {
  return (
    <div id="profile-image">
      <div id="profile-image-container">
        <img src={ProfilePhoto} className="border" />
        <div className="cameraIcon">
          <img src={CameraIcon} />
        </div>
      </div>
    </div>
  )
}

export default ProfileImage
