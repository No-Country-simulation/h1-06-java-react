import { Link } from 'react-router-dom'
import Dates from './components/Dates'
import './Turnos.css'
import { useEffect, useState } from 'react'

function Turnos() {
  const [dates, setDates] = useState([])

  useEffect(() => {
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
          dateList.push(`${day} ${month} ${year}, ${daysOfWeek[dayOfWeek]}`)
        }
        currentDate.setDate(currentDate.getDate() + 1)
      }

      return dateList
    }

    setDates(getDatesUntilEndOfMonth())
  }, [])

  return (
    <section className="contentHome">
      <h2>Turnos</h2>
      <section className="contentSchedule">
        {dates.map((date, index) => (
          <div key={index}>
            <p className="dateTurnos">{date}</p>
            <Dates />
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
