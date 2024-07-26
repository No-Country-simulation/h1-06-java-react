import { useEffect, useState } from 'react'
import Dates from './components/Dates'
import Title from './components/Title'
import './homeDoctor.css'
import { useUserLogin } from '../../../store/UserLogin'
import { GetAppointmentDoctor } from '../../../services/Doctor/GetAppointmentDoctor'

function HomeDoctor() {
  const { user } = useUserLogin()

  const [patients, setPatients] = useState([])

  useEffect(() => {
    const fetchPatients = async () => {
      const currentDate = new Date().toISOString().split('T')[0]
      const response = await GetAppointmentDoctor(user, currentDate)
      if (response && response.length > 0) {
        setPatients(response)
      }
    }
    fetchPatients()
  }, [user])

  const getCurrentDate = () => {
    const daysOfWeek = [
      'Domingo',
      'Lunes',
      'Martes',
      'Miércoles',
      'Jueves',
      'Viernes',
      'Sábado',
    ]
    const monthsOfYear = [
      'Enero',
      'Febrero',
      'Marzo',
      'Abril',
      'Mayo',
      'Junio',
      'Julio',
      'Agosto',
      'Septiembre',
      'Octubre',
      'Noviembre',
      'Diciembre',
    ]

    const currentDate = new Date()
    const dayOfWeek = daysOfWeek[currentDate.getDay()]
    const day = currentDate.getDate()
    const month = monthsOfYear[currentDate.getMonth()]
    const year = currentDate.getFullYear()

    return `${day} ${month} ${year}, ${dayOfWeek}`
  }

  const currentDate = getCurrentDate()

  return (
    <div className="contentHome">
      <Title />
      <section className="contentSchedule">
        <div className="contentTitlesSchedule">
          <h3 className="titleSchedule">Agenda del día</h3>
          <p className="patientSchedule">{patients.length} pacientes</p>
        </div>
        <p className="dateSchedule">{currentDate}</p>
        {patients.length > 0 ? (
          <Dates patients={patients} />
        ) : (
          <p className="NoCitas">Hoy no tienes citas agendadas</p>
        )}
      </section>
    </div>
  )
}

export default HomeDoctor
