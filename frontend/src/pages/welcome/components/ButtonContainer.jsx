import { Link } from "react-router-dom";
import Buttons from "../../../components/Buttons";

function ButtonContainer() {
  return (
    <div id="button-container">
      <div id="welcome-buttons" className="flex-column-center gapButtons">
        <Link to={"/login"}>
          <Buttons label="Iniciar Sesión" variant="primary" value="login" />
        </Link>
        <Link to={"/signup"}>
          <Buttons label="Regístrese" variant="secondary" />
        </Link>
      </div>
    </div>
  );
}

export default ButtonContainer;
