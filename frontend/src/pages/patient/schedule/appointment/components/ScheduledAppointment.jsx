import { useState } from "react";
import Notifications from "../../../../../components/Notifications/Notifications";
import CardNotification from "../../../home/components/CardNotification";
import { useUserPatientAppointment } from "../../../../../store/PatientStore/UserPatientAppointment";

function ScheduledAppointment() {
  const { appointment } = useUserPatientAppointment();
  return (
    <div id="scheduled-appointment">
      <div id="scheduled-appointment-container" className="container">
        <div id="scheduled-appointment-header">
          <h2>Cita programadas</h2>
        </div>
        <div id="scheduled-appointment-content-notifications">
          {Array.isArray(appointment) &&
            appointment.map((notification, index) => (
              <CardNotification
                key={index}
                type={notification.type}
                title={notification.title}
                time={notification.time}
                date={notification.date}
              />
            ))}
        </div>
      </div>
    </div>
  );
}

export default ScheduledAppointment;
