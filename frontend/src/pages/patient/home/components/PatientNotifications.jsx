import { useState } from "react";
import CardNotification from "./CardNotification";

function PatientNotifications() {
  const [notificationsMessage] = useState([
    {
      type: "info",
      title: "Bienvenido",
      time: "10:00",
    },
    {
      type: "alert",
      title: "Turno pendiente",
      time: "09:30",
    },
    {
      type: "warning",
      title: "Actualización de datos",
      time: "14:00",
    },
  ]);
  return (
    <div id="patientNotifications">
      <div>
        <h1>Notificaciones</h1>
      </div>
      <div id="patient-notifications-container">
        <div className="patient-notifications">
          {notificationsMessage.map((notification, index) => (
            <CardNotification
              key={index}
              type={notification.type}
              title={notification.title}
              time={notification.time}
            />
          ))}
        </div>
      </div>
    </div>
  );
}

export default PatientNotifications;
