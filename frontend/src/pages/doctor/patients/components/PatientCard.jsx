/* eslint-disable react/prop-types */
import profileImage from '/public/assets/images/profile.png'
import arrowImage from '/public/assets/icons/arrowRight.svg'

function PatientCard({ name, surname, dni, onClick }) {
  return (
    <div className="linkPatients" onClick={onClick}>
      <article className="contentPatientCardPatients">
        <div className="contentImgAndInfoPatientCardPatients">
          <img
            src={profileImage}
            className="imgPatientCardPatients"
            alt="Patient"
          />
          <div className="contentInfoPatientCardPatients">
            <p className="namePatientCardPatients">{name}</p>
            <p className="surnamePatientCardPatients">{surname}</p>
            <p className="dniPatientCardPatients">{dni}</p>
          </div>
        </div>
        <img
          src={arrowImage}
          className="arrowPatientCardPatients"
          alt="Arrow"
        />
      </article>
    </div>
  )
}

export default PatientCard
