import LogoContainer from "../welcome/components/LogoContainer";
import LoginForm from "./components/LoginForm";
import SignupContainer from "./components/SignupContainer";
import "./Login.css";

function Login() {
  return (
    <div id="login" className="flex-column-center">
      <LogoContainer />
      <div id="welcome-title">
        <h1 className="just-another-hand-regular">Bienvenido!</h1>
      </div>
      <LoginForm />
      <SignupContainer />
    </div>
  );
}

export default Login;
