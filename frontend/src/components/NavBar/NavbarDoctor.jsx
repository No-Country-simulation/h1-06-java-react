import { Link } from 'react-router-dom'
import homeImage from '../../../public/assets/icons/house.svg'
import profileImage from '../../../public/assets/images/profile.png'
import turnosImage from '../../../public/assets/icons/turnos.svg'
import pacientesImage from '../../../public/assets/icons/pacientes.svg'
import './NavbarDoctor.css'

function NavBar() {
  return (
    <div id="navBar">
      <div id="navBar-container" className="navBarContainer interFont">
        <Link
          to="/doctor/home"
          id="navBar-inicio"
          className="flex-column-center"
        >
          <img src={homeImage} />
          Inicio
        </Link>
        <Link
          to="/doctor/turnos"
          id="navBar-salud"
          className="flex-column-center"
        >
          <img src={turnosImage} />
          Turnos
        </Link>
        <Link
          to="/doctor/pacientes"
          id="navBar-calendar"
          className="flex-column-center"
        >
          <img src={pacientesImage} />
          Pacientes
        </Link>
        <Link
          to="/doctor/perfil"
          id="navBar-profile"
          className="flex-column-center"
        >
          <img src={profileImage} className="profile-profile-image" />
          Perfil
        </Link>
      </div>
    </div>
  )
}

export default NavBar