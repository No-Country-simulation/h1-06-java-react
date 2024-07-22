import { useState, useEffect } from "react";
import Buttons from "../../../../../components/Buttons/Buttons";
import { GetSpecialtiesFromDoctor } from "../../../../../services/Patient/GetSpecialtiesFromDoctor";
import createSpaces from "../../../../../hooks/createSpaces";
import { GetDoctorBySpecialty } from "../../../../../services/Patient/GetDoctorBySpecialty";

function ScheduleForm() {
  const [doctorBySpecialty, setDoctorBySpecialty] = useState([]);
  const [specialties, setSpecialties] = useState([]);
  const [scheduleForm, setScheduleForm] = useState({
    specialty: "",
    specialistName: "",
    date: null,
    time: "",
  });

  useEffect(() => {
    const GetSpecialties = async () => GetSpecialtiesFromDoctor();
    GetSpecialties().then((response) => setSpecialties(response));
  }, []);

  useEffect(() => {
    const GetDoctorSpecialty = async () => {
      const response = await GetDoctorBySpecialty(scheduleForm.specialty);
      setDoctorBySpecialty(response);
    };

    GetDoctorSpecialty();
  }, [scheduleForm.specialty]);

  return (
    <div id="schedule-form">
      <div id="schedule-form-container">
        <form className="form">
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
              {specialties.length > 0
                ? createSpaces(specialties).map((specialty) => (
                    <option key={specialty} value={specialty}>
                      {specialty}
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
              value={scheduleForm.specialistName}
              onChange={(e) => {
                setScheduleForm({
                  ...scheduleForm,
                  specialistName: e.target.value,
                });
              }}
            >
              {doctorBySpecialty.length > 0 ? (
                doctorBySpecialty.map((doctor) => (
                  <option key={doctor} value={doctor}>
                    {doctor}
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
            <Buttons type="submit" label="Agendar Cita"></Buttons>
          </div>
        </form>
      </div>
    </div>
  );
}

export default ScheduleForm;
