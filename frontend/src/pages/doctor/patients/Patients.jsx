import Search from './components/Search'
import './Patients.css'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

function Patients() {
  const [patients, setPatients] = useState([])
  const history = useNavigate()

  const handlePatientClick = (patient) => {
    history.push(`/doctor/historialPaciente/${patient.id}`)
  }

  return (
    <section className="contentPatients">
      <h2 className="titlePatients">Pacientes</h2>
      <p className="textPatients">Buscar paciente</p>
      <Search setPatients={setPatients} />
      <div className="patientList">
        {patients.length > 0 ? (
          patients.map((patient) => (
            <div
              key={patient.id}
              className="patientItem"
              onClick={() => handlePatientClick(patient)}
            >
              <p>
                {patient.name} {patient.surname}
              </p>
              <p>{patient.dni}</p>
            </div>
          ))
        ) : (
          <p>No se encontraron pacientes</p>
        )}
      </div>
    </section>
  )
}

export default Patients
