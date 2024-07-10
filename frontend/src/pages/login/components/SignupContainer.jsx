import { Link } from "react-router-dom";
function SignupContainer() {
  return (
    <div id="sign-up-container" className="inputMarginTop">
      <div id="signup" className="flex-row-evenly">
        <span>No tienes cuenta?</span>
        <Link to="/register">Registrate</Link>
      </div>
    </div>
  );
}

export default SignupContainer;
