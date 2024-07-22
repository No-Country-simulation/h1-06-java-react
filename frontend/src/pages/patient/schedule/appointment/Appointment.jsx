import ScheduledAppointment from "./components/ScheduledAppointment";
import "./Appointment.css";
import Titles from "../../../../components/Titles/Titles";
import ScheduleForm from "./components/ScheduleForm";

function Appointment() {
  return (
    <div id="appointment">
      <Titles title="Citas" url="/patient/home"></Titles>
      <div id="appointment-container">
        <ScheduledAppointment />
        <div id="appointment-divider">
          <h1>Agendar cita</h1>
          <p>
            Recuerda cancelar la cita 12 horas antes en caso de no concurrir.
          </p>
        </div>
        <ScheduleForm />
      </div>
    </div>
  );
}

export default Appointment;
