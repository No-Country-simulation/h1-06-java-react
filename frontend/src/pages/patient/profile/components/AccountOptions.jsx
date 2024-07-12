import { Link } from "react-router-dom"


function AccountOptions() {
  return (
    <div id="medical-data">
    <div id="medical-data-container">
      <div id="medical-data-header" className="flex-row">
        <div id="medical-data-title">
          <h2>Cuenta</h2>
        </div>
        <div id="medical-data-edit"></div>
      </div>
      <div id="medical-data-content" className="flex-column">
        <Link to={"/patient/prepaga"}>Ayuda</Link>
        <Link to={"/patient/help"}>Dar de baja la cuenta</Link>
        <Link to={"/"}>Cerrar sesion</Link>
      </div>
    </div>
  </div>
  )
}

export default AccountOptions