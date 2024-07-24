import { useEffect, useState } from 'react'
import Dates from './components/Dates'
import Title from './components/Title'
import './homeDoctor.css'
import { useUserLogin } from '../../../store/UserLogin'

function HomeDoctor() {
  const { user } = useUserLogin()

  const [patients, setPatients] = useState([])

  const formatDate = (date) => {
    return date.toISOString().split('.')[0].replace(/:/g, '%3A')
  }

  useEffect(() => {
    const fetchPatients = async () => {
      const id = user.id
      console.log(id)
      const currentDate = new Date()
      const startOfDay = new Date(currentDate.setHours(8, 0, 0, 0))
      const endOfDay = new Date(currentDate.setHours(12, 0, 0, 0))

      const initialDate = formatDate(startOfDay)
      const finishDate = formatDate(endOfDay)

      try {
        const response = await fetch(
          `http://localhost:7082/api/v1/appointment/doctor/${id}/${initialDate}/${finishDate}/true`
        )
        const data = await response.json()
        setPatients(data)
      } catch (error) {
        console.error('Error fetching patients:', error)
      }
    }

    fetchPatients()
  }, [])

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
        <Dates patients={patients} />
      </section>
    </div>
  )
}

export default HomeDoctor
