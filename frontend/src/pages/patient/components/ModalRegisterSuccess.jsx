/* eslint-disable react/prop-types */
import { Link } from "react-router-dom";
import "./ModalRegisterSuccess.css";
import Buttons from "../../../components/Buttons/Buttons";

function ModalRegisterSuccess({ message, linkTo, buttonLabel, onClick }) {
  return (
    <div id="modal-register-success">
      <div id="modal-register-success-container" className="flex-column">
      <span>{message}</span>
        <Link to={linkTo}>
          <Buttons label={buttonLabel} onClick={onClick}></Buttons>
        </Link>
      </div>
    </div>
  );
}

export default ModalRegisterSuccess;
