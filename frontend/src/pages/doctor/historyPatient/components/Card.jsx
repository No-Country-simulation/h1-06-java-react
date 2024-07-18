import microphone from '/public/assets/icons/microphone.svg'

// eslint-disable-next-line react/prop-types
function Card({ title, text, icon }) {
  return (
    <section className="contentCard">
      <img src={microphone} className="microCard" />
      <div className="contentTextCard">
        <div className="contentTitleCard">
          <img src={icon} className="iconCard" />
          <h5 className="titleCard">{title}</h5>
        </div>
        <p className="textCard">{text}</p>
      </div>
    </section>
  )
}

export default Card
