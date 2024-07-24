import { useState } from "react";
import CardNotification from "./CardNotification";
import { useEffect } from "react";
import { useUserLogin } from "../../../../store/UserLogin";
import { GetAppointment } from "../../../../services/Patient/GetAppointment";
import { useUserPatientAppointment } from "../../../../store/PatientStore/UserPatientAppointment";

function PatientNotifications() {
  const { user } = useUserLogin();
  const { appointment, setAppointment } = useUserPatientAppointment();
  //const [notificationsMessage, setNotificationsMessage] = useState([]);

  useEffect(() => {    const fetchAppointment = async () => {
    const response = await GetAppointment(user);
    console.log("RESPONSE: ", response);

    const now = new Date();
    const in72Hours = new Date(now.getTime() + 72 * 60 * 60 * 1000);

    const transformedResponse = response
      .filter(appointment => {
        const appointmentDate = new Date(appointment.date);
        return appointmentDate >= now && appointmentDate <= in72Hours;
      })
      .map(appointment => ({
        type: "Turno",
        title: `Cita con Dr. ${appointment.doctorId.name} ${appointment.doctorId.surname}`,
        time: new Date(appointment.date).toLocaleTimeString([], {
          hour: "2-digit",
          minute: "2-digit",
        }),
        date: new Date(appointment.date).toLocaleDateString(),
        day: new Date(appointment.date).getDay(),
        month: new Date(appointment.date).getMonth(),
        year: new Date(appointment.date).getFullYear(),
      }));

      setAppointment(transformedResponse);
  };

  fetchAppointment();
}, [user]);

  return (
    <div id="patientNotifications">
      <div>
        <h1>Notificaciones</h1>
      </div>
      <div id="patient-notifications-container">
        <div className="patient-notifications">
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

export default PatientNotifications;
