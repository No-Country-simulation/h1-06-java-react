import { useState } from "react";
import Buttons from "../../components/Buttons/Buttons";
import "./PasswordRecovery.css";
import { Link, Navigate, useHref } from "react-router-dom";

function PasswordRecovery() {
  const [email, setEmail] = useState("");
  const [changeScreen, setChangeScreen] = useState(false);
  const sendEmailRecovery = async (event) => {
    event.preventDefault();
    try {
      const apiUrl = `http://3.12.169.103:8080/api/v1/login/password/${email}`;
      await fetch(apiUrl, { method: 'POST' });
        setChangeScreen(true);
    } catch (error) {
      console.error(error);
    }
  };
  return (
    <div id="password-recovery">
      <div id="password-recovery-container">
        {!changeScreen ? (
          <form className="gap-form" onSubmit={sendEmailRecovery}>
            <div className="flex-column centerClass">
              <label>Ingresa tu email</label>
              <input type="email" onChange={(e) => setEmail(e.target.value)} />
            </div>
            <Buttons  type="submit" label="Enviar"></Buttons>
          </form>
        ) : (
          <div className="flex-column">
            <h1>Revisa tu correo por favor</h1>
            <Link to="/login">
              <Buttons label="Volver"></Buttons>
            </Link>
          </div>
        )}
      </div>
    </div>
  );
}

export default PasswordRecovery;
