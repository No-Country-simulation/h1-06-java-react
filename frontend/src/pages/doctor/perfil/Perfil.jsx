import AccountOptions from './components/AccountOptions'
import PersonalData from './components/PersonalData'
import ProfileImage from './components/ProfileImage'
import './Perfil.css'
import Titles from '../../../components/Titles/Titles'

function Perfil() {
  return (
    <div id="profile">
      <Titles title="Mi Perfil" url="/doctor/home"></Titles>
      <div id="profile-container">
        <ProfileImage />
        <PersonalData />
        <hr className="profile-hr" />
        <AccountOptions />
      </div>
    </div>
  )
}

export default Perfil
