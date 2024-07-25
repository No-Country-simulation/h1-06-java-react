/* eslint-disable react/prop-types */
import profileImage from '/public/assets/images/profile.png'
import arrowImage from '/public/assets/icons/arrowRight.svg'
import { Link } from 'react-router-dom'

function PatientCard({ patient }) {
  return (
    <Link to="/doctor/historialPaciente" className="link">
      <article className="contentPatientCard">
        <div className="contentImgAndInfoPatientCard">
          <img src={profileImage} className="imgPatientCard" />
          <div className="contentInfoPatientCard">
            <p className="namePatientCard">{patient.name}</p>
            <p className="hourPatientCard">{patient.appointmentTime}</p>
          </div>
        </div>
        <img src={arrowImage} className="arrowPatientCard" />
      </article>
    </Link>
  )
}

export default PatientCard
