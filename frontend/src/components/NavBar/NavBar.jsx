import { Link } from "react-router-dom";
import homeImage from "../../../public/assets/icons/house.svg";
import saludImage from "../../../public/assets/icons/salud.svg";
import calendarImage from "../../../public/assets/icons/calendar.svg";
import profileImage from "../../../public/assets/images/profile.png"
import "./NavBar.css";
function NavBar() {
  return (
    <div id="navBar">
      <div id="navBar-container">
        <Link id="navBar-inicio" className="flex-column-center">
          <img src={homeImage} />
          Inicio
        </Link>
        <Link id="navBar-salud" className="flex-column-center">
          <img src={saludImage} />
          Salud
        </Link>
        <Link id="navBar-calendar" className="flex-column-center">
          <img src={calendarImage} />
          Cita
        </Link>
        <Link id="navBar-profile" className="flex-column-center">
          <img src={profileImage} className="profile-profile-image" />
          Perfil
        </Link>
      </div>
    </div>
  );
}

export default NavBar;
