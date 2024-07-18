import Search from './components/Search'
import './Patients.css'

function Patients() {
  return (
    <section className="contentPatients">
      <h2 className="titlePatients">Pacientes</h2>
      <p className="textPatients">DNI del paciente</p>
      <Search />
    </section>
  )
}

export default Patients
