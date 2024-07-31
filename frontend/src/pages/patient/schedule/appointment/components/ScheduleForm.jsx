import { useState, useEffect } from 'react'
import Buttons from '../../../../../components/Buttons/Buttons'
import { GetSpecialtiesFromDoctor } from '../../../../../services/Patient/GetSpecialtiesFromDoctor'
import createSpaces from '../../../../../hooks/createSpaces'
import { GetDoctorBySpecialty } from '../../../../../services/Patient/GetDoctorBySpecialty'
import { useUserLogin } from '../../../../../store/UserLogin'
import { CreateAppointment } from '../../../../../services/Patient/CreateAppointment'
import { useNavigate } from 'react-router-dom'

function ScheduleForm() {
  const navigate = useNavigate()
  const { user } = useUserLogin()
  const [doctorBySpecialty, setDoctorBySpecialty] = useState([])
  const [showModal, setShowModal] = useState(false)
  const [specialties, setSpecialties] = useState([])
  const [scheduleForm, setScheduleForm] = useState({
    specialty: '',
    patientId: '',
    doctorId: '',
    date: null,
    time: '',
    observations: '',
  })
  const [loading, setLoading] = useState(false)

  useEffect(() => {
    const fetchSpecialties = async () => {
      const response = await GetSpecialtiesFromDoctor()
      setSpecialties(response)
    }
    fetchSpecialties()
  }, [])

  useEffect(() => {
    const fetchDoctors = async () => {
      setLoading(true);
      const response = await GetDoctorBySpecialty(scheduleForm.specialty, user);
      setDoctorBySpecialty(response);
      scheduleForm.doctorId = response[0]?.id;
      setLoading(false);
    };
    fetchDoctors();
  }, [scheduleForm.specialty, user]);

  /*   useEffect(() => {
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
  }); */

  if (loading) {
    return <p>Cargando...</p>;
  }

  const onSubmit = async (e) => {
    e.preventDefault()
    const formattedDateTime = formatDateTime(
      scheduleForm.date,
      scheduleForm.time
    )
    const formData = {
      ...scheduleForm,
      patientId: user.id,
      date: formattedDateTime,
    }
    const response = await CreateAppointment(formData, user)

    if (response && response.id) {
      navigate('/patient/home')
    } else {
      setShowModal(true)
    }
  }

  const formatDateTime = (date, time = '08:00') => {
    return `${date}T${time}`
  }

  const getAvailableHours = () => {
    let availableHours = []

    const selectedDoctor = doctorBySpecialty.find(
      (doctor) => doctor.id === scheduleForm.doctorId
    )

    if (selectedDoctor) {
      if (selectedDoctor.morning) {
        availableHours = [
          ...availableHours,
          ...Array.from(
            { length: 6 },
            (_, index) => `${index + 6 < 10 ? '0' : ''}${index + 6}:00`
          ),
        ]
      }

      if (selectedDoctor.afternoon) {
        availableHours = [
          ...availableHours,
          ...Array.from(
            { length: 6 },
            (_, index) => `${index + 12 < 10 ? '0' : ''}${index + 12}:00`
          ),
        ]
      }

      if (selectedDoctor.evening) {
        availableHours = [
          ...availableHours,
          ...Array.from(
            { length: 6 },
            (_, index) => `${index + 18 < 10 ? '0' : ''}${index + 18}:00`
          ),
        ]
      }
    }

    return availableHours
  }

  const availableHours = getAvailableHours()

  return (
    <div id="schedule-form">
      <div id="schedule-form-container">
        <form className="form" onSubmit={onSubmit}>
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
                })
              }}
            >
              <option value="">Seleccione una especialidad</option>
              {specialties.map((specialty) => (
                <option key={specialty} value={specialty}>
                  {createSpaces(specialty)}
                </option>
              ))}
            </select>
          </div>
          <div className="flex-column">
            <label>Nombre del especialista</label>
            <select
              name="specialistName"
              id="specialistName"
              className="inputLayout"
              value={scheduleForm.doctorId}
              onChange={(e) =>
                setScheduleForm({
                  ...scheduleForm,
                  doctorId: e.target.value,
                })
              }
            >
              <option value="">Seleccione un especialista</option>
              {doctorBySpecialty.length > 0 ? (
                doctorBySpecialty.map((doctor) => (
                  <option key={doctor.id} value={doctor.id}>
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
              <select
                value={scheduleForm.time}
                onChange={(e) =>
                  setScheduleForm({ ...scheduleForm, time: e.target.value })
                }
              >
                <option value="">Seleccione una hora</option>
                {availableHours.map((hour, index) => (
                  <option key={index} value={hour}>
                    {hour}
                  </option>
                ))}
              </select>
            </div>
          )}
          <div className="flex-column">
            <label>Observaciones</label>
            <textarea
              type="text"
              required
              className="inputLayout"
              value={scheduleForm.observations}
              onChange={(e) =>
                setScheduleForm({
                  ...scheduleForm,
                  observations: e.target.value,
                })
              }
            />
          </div>
          <Buttons type="submit" label="Agendar Cita" />
        </form>
        {showModal && (
          <div className="modal">
            <p>Error al agendar la cita. Por favor, intente de nuevo.</p>
            <button onClick={() => setShowModal(false)}>Cerrar</button>
          </div>
        )}
      </div>
    </div>
  )
}

export default ScheduleForm
