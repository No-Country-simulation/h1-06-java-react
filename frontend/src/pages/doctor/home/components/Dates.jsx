/* eslint-disable react/prop-types */
import PatientCard from './PatientCard'

function Dates({ patients }) {
  const sortedPatients = [...patients].sort(
    (a, b) => new Date(a.date) - new Date(b.date)
  )

  return (
    <section className="contentPatientsDate">
      {sortedPatients.length > 0 ? (
        sortedPatients.map((patient, index) => (
          <div key={index} className="contentHourAndPatient">
            <div className="contentHourDate">
              <p className="hourDate">
                {new Date(patient.date)
                  .toLocaleTimeString([], {
                    hour: '2-digit',
                    minute: '2-digit',
                    hour12: true,
                  })
                  .replace(/\.$/, '')}
              </p>
            </div>
            <PatientCard patient={patient} />
          </div>
        ))
      ) : (
        <p>Hoy no tienes citas agendadas</p>
      )}
    </section>
  )
}

export default Dates
