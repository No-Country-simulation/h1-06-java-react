/* eslint-disable react/prop-types */
import profileImage from '/public/assets/images/profile.png'
import arrowImage from '/public/assets/icons/arrowRight.svg'
import { Link } from 'react-router-dom'

function PatientCard({ patient }) {
  const appointmentDate = new Date(patient.date)
  const appointmentTime = appointmentDate.toLocaleTimeString([], {
    hour: '2-digit',
    minute: '2-digit',
  })

  return (
    <Link to={`/doctor/historialPaciente/${patient.id}`} className="link">
      <article className="contentPatientCard">
        <div className="contentImgAndInfoPatientCard">
          <img src={profileImage} className="imgPatientCard" />
          <div className="contentInfoPatientCard">
            <p className="namePatientCard">
              {patient.patientId.name} {patient.patientId.surname}
            </p>
            <p className="hourPatientCard">{appointmentTime}</p>
          </div>
        </div>
        <img src={arrowImage} className="arrowPatientCard" />
      </article>
    </Link>
  )
}

export default PatientCard
