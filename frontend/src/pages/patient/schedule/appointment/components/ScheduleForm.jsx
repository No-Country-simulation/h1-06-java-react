import { useState, useEffect } from "react";
import Buttons from "../../../../../components/Buttons/Buttons";
import { GetSpecialtiesFromDoctor } from "../../../../../services/Patient/GetSpecialtiesFromDoctor";
import createSpaces from "../../../../../hooks/createSpaces";
import { GetDoctorBySpecialty } from "../../../../../services/Patient/GetDoctorBySpecialty";
import { useUserLogin } from "../../../../../store/UserLogin";
import { FindAvailableAppointmentsByDr } from "../../../../../services/Patient/FindAvailableAppointmentsByDr";
import { CreateAppointment } from "../../../../../services/Patient/CreateAppointment";

function ScheduleForm() {
  const { user } = useUserLogin();
  const [doctorBySpecialty, setDoctorBySpecialty] = useState([]);
  const [specialties, setSpecialties] = useState([]);
  const [scheduleForm, setScheduleForm] = useState({
    specialty: "",
    patientId: "",
    doctorId: "",
    date: null,
    observations: "",
  });

  useEffect(() => {
    const GetSpecialties = async () => GetSpecialtiesFromDoctor();
    GetSpecialties().then((response) => setSpecialties(response));
  }, []);

  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      const response = await GetDoctorBySpecialty(scheduleForm.specialty, user);
      setDoctorBySpecialty(response);
      setLoading(false);
    };

    if (scheduleForm.specialty !== "") {
      fetchData();
    }
  }, [scheduleForm.specialty]);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      const response = await FindAvailableAppointmentsByDr(
        doctorBySpecialty[0],
        user,
        scheduleForm.date
      );
      setScheduleForm({
        ...scheduleForm,
        time: response[0],
      });
      setLoading(false);
    };
  });

  if (loading) {
    return <p>Cargando...</p>;
  }


  const onSubmit = async (e, id) => {
    e.preventDefault();
    setScheduleForm({
      ...scheduleForm,
      patientId: id,
    })
    console.log(scheduleForm);
    const response = await CreateAppointment(scheduleForm, id);
    console.log(response);
  };
  return (
    <div id="schedule-form">
      <div id="schedule-form-container">
        <form className="form" onSubmit={(e) => onSubmit(e, user.id)}>
          <div className="flex-column">
            <label>Especialidad</label>
            <select
              name="specialty"
              id="specialty"
              className="inputLayout"
              value={scheduleForm.specialty}
              onChange={(e) => {
                setScheduleForm({
                  ...scheduleForm,
                  specialty: e.target.value,
                });
              }}
            >
              {specialties && specialties.length > 0
                ? specialties.map((specialty) => (
                    <option key={specialty} value={specialty}>
                      {createSpaces(specialty)}
                    </option>
                  ))
                : null}
            </select>
          </div>
          <div className="flex-column">
            <label>Nombre del especialista</label>
            <select
              name="specialistName"
              id="specialistName"
              className="inputLayout"
              onChange={(e) =>
                setScheduleForm({
                  ...scheduleForm,
                  doctorId: e.target.value,
                })
              }
            >
              {doctorBySpecialty.length > 0 ? (
                doctorBySpecialty.map((doctor) => (
                  <option
                    key={doctor.id}
                    value={doctor.id}
                  >
                    {doctor.name} {doctor.surname}
                  </option>
                ))
              ) : (
                <option>No hay especialistas</option>
              )}
            </select>
          </div>
          <div className="flex-column">
            <label>Selecciona disponibilidad</label>
            <input
              type="date"
              required
              className="inputLayout"
              onChange={(e) =>
                setScheduleForm({ ...scheduleForm, date: e.target.value })
              }
            />
          </div>
          {scheduleForm.date && (
            <div className="flex-column">
              <label>Hora</label>
              <select>
                {Array.from({ length: 24 }, (_, index) => {
                  const hour = `${index < 10 ? "0" : ""}${index}:00`;
                  return <option key={index}>{hour}</option>;
                })}
              </select>
            </div>
          )}
          <div>
            <div className="flex-column">
              <label>Observaciones</label>
              <textarea
                type="text"
                required
                className="inputLayout"
                onChange={(e) =>
                  setScheduleForm({ ...scheduleForm, observations: e.target.value })
                }
              />
            </div>
            <Buttons type="submit" label="Agendar Cita"></Buttons>
          </div>
        </form>
      </div>
    </div>
  );
}

export default ScheduleForm;
