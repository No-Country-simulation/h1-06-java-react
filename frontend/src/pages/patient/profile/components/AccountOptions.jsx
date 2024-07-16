import { Link } from "react-router-dom"


function AccountOptions() {
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
        <Link to={"/"}>Cerrar sesion</Link>
      </div>
    </div>
  </div>
  )
}

export default AccountOptions