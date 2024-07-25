import { useEffect, useState } from "react";
import userIcon from "../../../../public/assets/icons/User.svg";
import passwordIcon from "../../../../public/assets/icons/Lock.svg";
import { Link, useNavigate } from "react-router-dom";
import Buttons from "../../../components/Buttons/Buttons";
import { Login } from "../../../services/Login/Login";
import { useUserLogin } from "../../../store/UserLogin";

function LoginForm() {
  const navigate = useNavigate();
  const { setUser, user } = useUserLogin();
  const [rememberMe, setRememberMe] = useState(false);
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
    if (rememberMe) {
      localStorage.setItem("rememberedEmail", value.email);
      localStorage.setItem("rememberedPassword", value.password);
      localStorage.setItem("rememberMe", "true");
    } else {
      localStorage.removeItem("rememberedEmail");
      localStorage.removeItem("rememberedPassword");
      localStorage.removeItem("rememberMe");
    }

    console.log(response);
    console.log("USER", user);

    if (response.role === "[ROLE_PACIENTE]") {
      navigate("/patient/home");
    } else if (response.role === "[ROLE_DOCTOR]") {
      navigate("/doctor/home");
    }
  };

  useEffect(() => {
    const savedEmail = localStorage.getItem("rememberedEmail");
    const savedPassword = localStorage.getItem("rememberedPassword");
    const savedRememberMe = localStorage.getItem("rememberMe") === "true";

    if (savedRememberMe) {
      setValue({ email: savedEmail || "", password: savedPassword || "" });
      setRememberMe(true);
    }
  }, []);

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
            <img src={userIcon} alt="User Icon" />
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
            <img src={passwordIcon} alt="Password Icon" />
            <input
              type="password"
              placeholder="Contrasena"
              value={value.password}
              onChange={(e) => setValue({ ...value, password: e.target.value })}
            />
          </div>
        </div>
        <div className="flex-row-between rememberPassword inputMarginBottom">
          <div>
            <input
              type="checkbox"
              checked={rememberMe}
              onChange={(e) => setRememberMe(e.target.checked)}
            />
            <label>Recordar</label>
          </div>
          <div>
            <Link to={"/password-recovery"}>Olvidó su contraseña?</Link>
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
