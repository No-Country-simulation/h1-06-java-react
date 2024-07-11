import { useState } from "react";
import CardMenu from "./CardMenu";
function PatientMenu() {
  const [optionMenu] = useState([
    { title: "Solicitar Turnos", url: "/appointment" },
    { title: "Mis Turnos", url: "/calendar" },
    { title: "Historia Clinica", url: "/medical-history" },
    { title: "Medicamentos", url: "/medicines" },
  ]);
  return (
    <div id="patient-menu">
      <h1>Menu</h1>
      <div id="patient-menu-container">
        {optionMenu.map((cardInfo, index) => (
          <CardMenu key={index} title={cardInfo.title} url={cardInfo.url} />
        ))}
      </div>
    </div>
  );
}

export default PatientMenu;
