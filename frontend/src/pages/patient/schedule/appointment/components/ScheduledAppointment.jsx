import { useState } from "react";
import Notifications from "../../../../../components/Notifications/Notifications";
import CardNotification from "../../../home/components/CardNotification";
import { useUserPatientAppointment } from "../../../../../store/PatientStore/UserPatientAppointment";

function ScheduledAppointment() {
  const { appointment } = useUserPatientAppointment();

  const transformedResponse = appointment?.map((element) => ({
    type: "Turno",
    title: `Cita con Dr. ${element.doctorId.name} ${element.doctorId.surname}`,
    time: new Date(element.date).toLocaleTimeString([], {
      hour: "2-digit",
      minute: "2-digit",
    }),
    date: new Date(element.date).toLocaleDateString(),
    day: new Date(element.date).getDay(),
    month: new Date(element.date).getMonth(),
    year: new Date(element.date).getFullYear(),
  }));

  return (
    <div id="scheduled-appointment">
      <div id="scheduled-appointment-container" className="container">
        <div id="scheduled-appointment-header">
          <h2>Citas programadas</h2>
        </div>
        <div id="scheduled-appointment-content-notifications">
          {Array.isArray(transformedResponse) &&
            transformedResponse.map((notification, index) => (
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
