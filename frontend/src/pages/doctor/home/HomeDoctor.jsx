import Dates from './components/Dates'
import EventCard from './components/EventCard'
import Search from './components/Search'
import Title from './components/Title'
import './HomeDoctor.css'

function homeDoctor() {
  return (
    <div className="contentHome">
      <Title />
      <Search />
      <section className="contentSchedule">
        <div className="contentTitlesSchedule">
          <h3 className="titleSchedule">Agenda del día</h3>
          <p className="patientSchedule">{'number'} pacientes</p>
        </div>
        <p className="dateSchedule">5 Julio 24, Viernes</p>
        <Dates />
      </section>
      <section className="contentEvents">
        <h3 className="titleEvents">Eventos</h3>
        <div className="contentCardsEvents">
          <EventCard />
        </div>
      </section>
    </div>
  )
}

export default homeDoctor
