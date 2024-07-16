import profileImage from '/public/assets/images/profile.png'

function Title() {
  return (
    <section className='contentTitle'>
      <img src={profileImage} className='imgTitle' />
      <h2 className='textTitle'>Buenos dias, {'user'}!👋</h2>
    </section>
  )
}

export default Title
