import { Link, Navigate } from "react-router-dom";
import { useUserLogin } from "../../../../store/UserLogin";
import  DeletePatient  from "../../../../services/Patient/DeletePatient";
import { useState } from "react";

function AccountOptions() {
  const [showModal, setShowModal] = useState(false);
  const { user,setUser } = useUserLogin();

  const AccountHandler = () => {
    localStorage.clear();
    setUser({});
    Navigate("/login");
  };

  const deleteAccount = () => {
    DeletePatient(user);
    Navigate("/login");
  };
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
          <div onClick={() => deleteAccount()}>Dar de baja la cuenta</div>
          <div onClick={() => AccountHandler()}>Cerrar sesion</div>
        </div>
      </div>
    </div>
  );
}

export default AccountOptions;
