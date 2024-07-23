import { useUserLogin } from '../../../../store/UserLogin'
import profileImage from '/public/assets/images/profile.png'

function Title() {
  const { user } = useUserLogin()

  const getCurrentGreeting = () => {
    const currentHour = new Date().getHours()
    if (currentHour < 12) {
      return 'Buenos días'
    } else if (currentHour < 20) {
      return 'Buenas tardes'
    } else {
      return 'Buenas noches'
    }
  }

  const greeting = getCurrentGreeting()

  return (
    <section className="contentTitle">
      <img src={profileImage} className="imgTitle" />
      <h2 className="textTitle">
        {greeting}, {user ? user.name : 'usuario'}!👋
      </h2>
    </section>
  )
}

export default Title
