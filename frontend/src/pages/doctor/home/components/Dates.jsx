/* eslint-disable react/prop-types */
import PatientCard from './PatientCard'

function Dates({ patients }) {
  return (
    <section className="contentPatientsDate">
      <div className="contentHourDate">
        <p className="hourDate">8:00 am</p>
      </div>
      <div className="contentPatientsCard">
        {patients.map((patient, index) => (
          <PatientCard key={index} patient={patient} />
        ))}
      </div>
    </section>
  )
}

export default Dates
