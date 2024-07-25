import { useState } from "react";
import CardMenu from "./CardMenu";
import MedicHistoryIcon from "../../../../../public/assets/icons/hospital.svg";
import calendarIcon from "../../../../../public/assets/icons/calendarB.svg";
function PatientMenu() {
  const [optionMenu] = useState([
    {
      title: "Historia Clinica",
      url: "/patient/medical-history",
      icon: calendarIcon,
      textButton: "Ver detalle",
    },
    {
      title: "Consultas Médicas",
      url: "/patient/appointment",
      icon: MedicHistoryIcon,
      textButton: "Ir a citas",
    },
  ]);
  return (
    <div id="patient-menu">
      <h1>Menu</h1>
      <div id="patient-menu-container">
        {optionMenu.map((cardInfo, index) => (
          <CardMenu key={index} cardInfo={cardInfo} />
        ))}
      </div>
    </div>
  );
}

export default PatientMenu;
