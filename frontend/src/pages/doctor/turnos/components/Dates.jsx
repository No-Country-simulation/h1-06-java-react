/* eslint-disable react/prop-types */
import PatientCard from './PatientCard'

function Dates({ appointments }) {
  const sortedAppointments = appointments.sort(
    (a, b) => new Date(a.date) - new Date(b.date)
  )

  return (
    <section className="contentPatientsDate">
      {sortedAppointments.length > 0 ? (
        sortedAppointments.map((appointment, index) => (
          <div key={index} className="contentHourAndPatient">
            <div className="contentHourDate">
              <p className="hourDate">
                {new Date(appointment.date).toLocaleTimeString('en-GB', {
                  hour: '2-digit',
                  minute: '2-digit',
                  hour12: false,
                })}
              </p>
            </div>
            <div className="contentPatientsCardTurnos">
              <PatientCard
                id={`${appointment.id}`}
                name={`${appointment.patientId.name} ${appointment.patientId.surname}`}
                appointmentTime={new Date(appointment.date).toLocaleTimeString(
                  'en-GB',
                  { hour: '2-digit', minute: '2-digit', hour12: false }
                )}
              />
            </div>
          </div>
        ))
      ) : (
        <p>Hoy no tienes citas agendadas</p>
      )}
    </section>
  )
}

export default Dates
