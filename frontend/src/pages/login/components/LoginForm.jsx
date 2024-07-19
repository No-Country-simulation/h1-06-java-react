import { useState } from "react";
import userIcon from "../../../../public/assets/icons/User.svg";
import passwordIcon from "../../../../public/assets/icons/Lock.svg";
import { Link, useNavigate } from "react-router-dom";
import Buttons from "../../../components/Buttons/Buttons";
import { Login } from "../../../services/Login/Login";
import { useUserLogin } from "../../../store/UserLogin";

function LoginForm() {
  const navigate = useNavigate();
  const { setUser, user } = useUserLogin();
  const [value, setValue] = useState({
    email: "",
    password: "",
  });

  const handleLogin = async (e) => {
    e.preventDefault();
    const response = await Login(value.email, value.password);
    setUser(response);
    localStorage.setItem("user", JSON.stringify(response));
    console.log(response);
    console.log("USER", user);
    if (response.role === "[ROLE_PACIENTE]") {
      navigate("/patient/home");
    } else if (response.role === "[ROLE_DOCTOR]") {
      navigate("/doctor/home");
    }
  };

  return (
    <div id="login-form">
      <form
        action="submit"
        id="login-form-container"
        className="flex-column-center"
        onSubmit={handleLogin}
      >
        <div className="flex-column inputMarginBottom">
          <label>Email</label>
          <div className="input-div">
            <img src={userIcon} />
            <input
              type="email"
              placeholder="Usuario"
              value={value.email}
              onChange={(e) => setValue({ ...value, email: e.target.value })}
            />
          </div>
        </div>
        <div className="flex-column">
          <label>Contraseña</label>
          <div className="input-div">
            <img src={passwordIcon} />
            <input
              type="password"
              placeholder="Contrasena"
              value={value.password}
              onChange={(e) => setValue({ ...value, password: e.target.value })}
            />
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
