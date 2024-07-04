import { Link } from "react-router-dom";
function SignupContainer() {
  return (
    <div id="sign-up-container">
      <div id="signup">
        <span>No tienes cuenta?</span>
        <Link to={"#"}>Registrate</Link>
      </div>
    </div>
  );
}

export default SignupContainer;
