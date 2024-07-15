import profileImage from '/public/assets/images/profile.png'
import arrowImage from '/public/assets/icons/arrowRight.svg'

function PatientCard() {
  return (
    <article className="contentPatientCard">
      <div className="contentImgAndInfoPatientCard">
        <img src={profileImage} className="imgPatientCard" />
        <div className="contentInfoPatientCard">
          <p className="namePatientCard">Nombre del paciente</p>
          <p className="hourPatientCard">8:00 - 8:20</p>
        </div>
      </div>
      <img src={arrowImage} className="arrowPatientCard" />
    </article>
  )
}

export default PatientCard
