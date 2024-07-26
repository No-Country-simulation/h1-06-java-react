/* eslint-disable react/prop-types */
import profileImage from '/public/assets/images/profile.png'
import arrowImage from '/public/assets/icons/arrowRight.svg'
import { Link } from 'react-router-dom'

function PatientCard({ name, appointmentTime }) {
  return (
    <Link to="/doctor/historialPaciente" className="link">
      <article className="contentPatientCard">
        <div className="contentImgAndInfoPatientCard">
          <img src={profileImage} className="imgPatientCard" alt="Patient" />
          <div className="contentInfoPatientCard">
            <p className="namePatientCard">{name}</p>
            <p className="hourPatientCard">{appointmentTime}</p>
          </div>
        </div>
        <img src={arrowImage} className="arrowPatientCard" alt="Arrow" />
      </article>
    </Link>
  )
}

export default PatientCard
