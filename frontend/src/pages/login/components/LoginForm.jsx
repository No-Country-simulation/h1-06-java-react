import { useState } from "react";
import userIcon from "../../../../public/assets/icons/User.svg";
import passwordIcon from "../../../../public/assets/icons/Lock.svg";
import { Link } from "react-router-dom";
import Buttons from "../../../components/Buttons/Buttons";

function LoginForm() {
  const [value, setValue] = useState({
    username: "",
    password: "",
  });
  return (
    <div id="login-form">
      <form
        action="submit"
        id="login-form-container"
        className="flex-column-center"
      >
        <div className="flex-column inputMarginBottom">
          <label>Email</label>
          <div className="input-div">
            <img src={userIcon} />
            <input placeholder="Usuario" />
          </div>
        </div>
        <div className="flex-column">
          <label>Contraseña</label>
          <div className="input-div">
            <img src={passwordIcon} />
            <input placeholder="Contrasena" />
          </div>
        </div>
        <div className="flex-row-between rememberPassword  inputMarginBottom">
          <div>
            <input type="radio" />
            <label>Recordar</label>
          </div>
          <div className="">
            <Link to={"/forgotPassword"}>Olvidó su contraseña?</Link>
          </div>
        </div>
        <div id="login-button">
          <Buttons label="Iniciar Sesion" variant="primary" type="submit" />
        </div>
      </form>
    </div>
  );
}

export default LoginForm;
