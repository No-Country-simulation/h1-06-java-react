import { Link } from "react-router-dom";
import homeImage from "../../../public/assets/icons/house.svg";
import saludImage from "../../../public/assets/icons/salud.svg";
import calendarImage from "../../../public/assets/icons/calendar.svg";
import profileImage from "../../../public/assets/images/profile.png";
import "./NavBar.css";
import { useState } from "react";
import { useLocation } from "react-router-dom";

function NavBar() {
  let location = useLocation();
  const [navBarSelected, setNavBarSelected] = useState("home");

  console.log("name", location);
  return (
    <div id="navBar">
      <div id="navBar-container" className="navBarContainer interFont">
        <Link
          to={"/patient/home"}
          id="navBar-inicio"
          className={`flex-column-center nav ${
            location.pathname === "/patient/home" ? "activeNavbar" : ""
          }`}
          onClick={() => setNavBarSelected("home")}
        >
          <img src={homeImage} />
          Inicio
        </Link>
        <Link
          to={"/patient/medical-history"}
          id="navBar-salud"
          className={`flex-column-center nav ${
            location.pathname === "/patient/medical-history"
              ? "activeNavbar"
              : ""
          }`}
          onClick={() => setNavBarSelected("salud")}
        >
          <img src={saludImage} />
          Salud
        </Link>
        <Link
          to={"/patient/appointment"}
          id="navBar-calendar"
          className={`flex-column-center nav ${
            location.pathname === "/patient/appointment" ? "activeNavbar" : ""
          }`}
          onClick={() => setNavBarSelected("appointment")}
        >
          <img src={calendarImage} />
          Cita
        </Link>
        <Link
          to={"/patient/profile"}
          id="navBar-profile"
          className={`flex-column-center nav ${
            location.pathname === "/patient/profile" ? "activeNavbar" : ""
          }`}
          onClick={() => setNavBarSelected("profile")}
        >
          <img src={profileImage} className="profile-profile-image" />
          Perfil
        </Link>
      </div>
    </div>
  );
}

export default NavBar;
