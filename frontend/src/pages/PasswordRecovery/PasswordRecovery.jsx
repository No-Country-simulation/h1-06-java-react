import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Buttons from "../../components/Buttons/Buttons";
import "./PasswordRecovery.css";

function PasswordRecovery() {
  const [email, setEmail] = useState("");
  const [emailSent, setEmailSent] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleClick = async () => {
    const apiUrl = `http://3.12.169.103:8080/api/v1/login/password/${email}`;

    try {
      const response = await fetch(apiUrl, { method: "GET" });
      if (!response.ok) {
        throw new Error(`Error: ${response.status} ${response.statusText}`);
      }

      const data = await response.json();

      if (data) {
        console.log("Password recovery email sent successfully");
        setEmailSent(true);
        setTimeout(() => navigate("/login"), 2000);
      } else {
        throw new Error("Failed to send recovery email");
      }
    } catch (error) {
      console.error("There was a problem with the fetch operation:", error);
      setErrorMessage(error.message);
    }
  };

  return (
    <div id="password-recovery">
      <div id="password-recovery-container">
        {!emailSent ? (
          <form className="gap-form" onSubmit={(e) => e.preventDefault()}>
            <div className="flex-column centerClass">
              <label>Ingresa tu email</label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
            </div>
            <Buttons label="Enviar" onClick={handleClick} />
            {errorMessage && <p className="error-message">{errorMessage}</p>}
          </form>
        ) : (
          <div className="flex-column">
            <h1>Revisa tu correo por favor</h1>
            <Buttons label="Volver" onClick={() => navigate("/login")} />
          </div>
        )}
      </div>
    </div>
  );
}

export default PasswordRecovery;
