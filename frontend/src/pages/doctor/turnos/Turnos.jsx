import { Link } from 'react-router-dom'
import Dates from './components/Dates'
import './Turnos.css'
import { useEffect, useState } from 'react'
import { GetAppointmentDoctorMonth } from '../../../services/Doctor/GetAppointmentDoctorMonth'
import { useUserLogin } from '../../../store/UserLogin'

function Turnos() {
  const { user } = useUserLogin()
  const [appointments, setAppointments] = useState([])

  useEffect(() => {
    const fetchAppointments = async () => {
      const data = await GetAppointmentDoctorMonth(user)
      console.log(data)
      setAppointments(Array.isArray(data) ? data : [])
    }

    fetchAppointments()
  }, [user])

  const getDatesUntilEndOfMonth = () => {
    const currentDate = new Date()
    const endOfMonth = new Date(
      currentDate.getFullYear(),
      currentDate.getMonth() + 1,
      0
    )

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

    const dateList = []

    while (currentDate <= endOfMonth) {
      const dayOfWeek = currentDate.getDay()
      if (dayOfWeek !== 0 && dayOfWeek !== 6) {
        const day = currentDate.getDate()
        const month = monthsOfYear[currentDate.getMonth()]
        const year = currentDate.getFullYear()
        dateList.push({
          date: `${day} ${month} ${year}, ${daysOfWeek[dayOfWeek]}`,
          appointments: appointments.filter(
            (appointment) =>
              new Date(appointment.date).toDateString() ===
              new Date(currentDate).toDateString()
          ),
        })
      }
      currentDate.setDate(currentDate.getDate() + 1)
    }

    const additionalDays = 7
    for (let i = 0; i < additionalDays; i++) {
      const nextDate = new Date(currentDate)
      const dayOfWeek = nextDate.getDay()
      if (dayOfWeek !== 0 && dayOfWeek !== 6) {
        const day = nextDate.getDate()
        const month = monthsOfYear[nextDate.getMonth()]
        const year = nextDate.getFullYear()
        dateList.push({
          date: `${day} ${month} ${year}, ${daysOfWeek[dayOfWeek]}`,
          appointments: appointments.filter(
            (appointment) =>
              new Date(appointment.date).toDateString() ===
              new Date(nextDate).toDateString()
          ),
        })
      }
      currentDate.setDate(currentDate.getDate() + 1)
    }

    return dateList
  }

  const dates = getDatesUntilEndOfMonth()

  return (
    <section className="contentHome">
      <h2>Turnos</h2>
      <section className="contentSchedule">
        {dates.map((dateInfo, index) => (
          <div key={index}>
            <p className="dateTurnos">{dateInfo.date}</p>
            <Dates appointments={dateInfo.appointments} />
          </div>
        ))}
      </section>
      <div className="contntBtnsTurnos">
        <Link to="/doctor/turnos/cancel" className="linkCalendarTurnos">
          <button className="btnCalendarTurnos">Ver calendario</button>
        </Link>
        <Link to="/doctor/turnos/cancel" className="linkCancelTurnos">
          <button className="btnCancelTurnos">Cancelar cita</button>
        </Link>
      </div>
    </section>
  )
}

export default Turnos
