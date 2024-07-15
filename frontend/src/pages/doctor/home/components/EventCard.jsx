import profileImage from '/public/assets/images/profile.png'
import arrowImage from '/public/assets/icons/arrowRight.svg'

function EventCard() {
  return (
    <article className="contentEventCard">
      <div className="contentImgAndInfoEventCard">
        <img src={profileImage} className="imgEventCard" />
        <div className="contentTextsEventCard">
          <p className="nameEventCard">Nombre del paciente</p>
          <p className="hourEventCard">8:00 - 8:20</p>
        </div>
      </div>
      <img src={arrowImage} className="arrowEventCard" />
    </article>
  )
}

export default EventCard
