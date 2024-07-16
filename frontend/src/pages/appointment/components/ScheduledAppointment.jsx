import { useState } from "react";
import Notifications from "../../../components/Notifications/Notifications";

function ScheduledAppointment() {
  const [notificationExample] = useState([
    {
      type: "info",
      title: "Cita programada",
      time: "10:00",
      url: "/doctor/home",
    },
    {
      type: "alert",
      title: "Cita programada",
      time: "09:30",
      url: "/doctor/home",
    },
    {
      type: "warning",
      title: "Cita programada",
      time: "14:00",
      url: "/doctor/home",
    },
  ]);
  return (
    <div id="scheduled-appointment">
      <div id="scheduled-appointment-container" className="container">
        <div id="scheduled-appointment-header">
          <h2>Cita programadas</h2>
        </div>
        <div id="scheduled-appointment-content-notifications">
          {notificationExample.map((notification, index) => (
            <Notifications
              key={index}
              type={notification.type}
              title={notification.title}
              time={notification.time}
              url={notification.url}
            />
          ))}
        </div>
      </div>
    </div>
  );
}

export default ScheduledAppointment;
