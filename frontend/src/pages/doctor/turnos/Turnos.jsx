import { Link } from 'react-router-dom'
import Dates from '../home/components/Dates'
import './Turnos.css'

function Turnos() {
  return (
    <section className="contentHome">
      <h2>Turnos</h2>
      <section className="contentSchedule">
        <Dates />
        <Dates />
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
