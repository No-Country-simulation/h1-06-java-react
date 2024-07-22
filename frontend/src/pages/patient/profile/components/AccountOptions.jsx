import { Link, Navigate } from "react-router-dom"
import { useUserLogin } from "../../../../store/UserLogin";



function AccountOptions() {
  const { setUser } = useUserLogin();
  
const AccountHandler = () => {
  localStorage.clear();
  setUser({});
  Navigate("/login");
}
  return (
    <div id="account-data">
    <div id="account-data-container">
      <div id="account-data-header" className="flex-row">
        <div id="account-data-title">
          <h2>Cuenta</h2>
        </div>
        <div id="account-data-edit"></div>
      </div>
      <div id="account-data-content" className="flex-column">
        <Link to={"/patient/prepaga"}>Ayuda</Link>
        <Link to={"/patient/help"}>Dar de baja la cuenta</Link>
        <div onClick={() => AccountHandler()}>Cerrar sesion</div>
      </div>
    </div>
  </div>
  )
}

export default AccountOptions