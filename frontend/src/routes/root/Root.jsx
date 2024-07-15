import { Outlet, ScrollRestoration } from 'react-router-dom'
// import NavBar from '../../components/NavBar/NavBar'
import NavbarDoctor from '../../components/NavBar/NavbarDoctor'

function Root() {
  return (
    <div>
      <Outlet />
      <ScrollRestoration />
      {/* <NavBar /> */}
      <NavbarDoctor />
    </div>
  )
}

export default Root
