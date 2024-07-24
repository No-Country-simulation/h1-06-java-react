/* eslint-disable react/prop-types */
import PatientCard from './PatientCard'

function Dates() {
  return (
    <section className="contentPatientsDate">
      <div className="contentHourDate">
        <p className="hourDate">8:00 am</p>
      </div>
      <div className="contentPatientsCard">
        <PatientCard />
        <PatientCard />
        <PatientCard />
      </div>
    </section>
  )
}

export default Dates
