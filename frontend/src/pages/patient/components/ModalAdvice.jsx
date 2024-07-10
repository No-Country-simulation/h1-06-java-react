/* eslint-disable react/prop-types */
import Buttons from "../../../components/Buttons/Buttons";
import "./ModalAdvice.css";
import ModalForm from "./ModalForm";
function ModalAdvice({ ...props }) {
  function onClickHandler() {
    props.setIsShownModal(false);
    props.setIsModalFormSelect(true);
  }
  return (
    <div id="modal-advice">
      {props.isShownModal === true ? (
        <div id="modal-advice-container">
          <p>Este formulario requiere acompañamiento de un tutor.</p>
          <p>
            Por favor diligencie los siguientes datos para continuar con el
            registro.
          </p>
          <div>
            <Buttons
              variant="primary"
              label={"Continuar"}
              onClick={onClickHandler}
            ></Buttons>
          </div>
        </div>
      ) : (
        props.isModalFormSelect && <ModalForm setIsModalFormSelect={props.setIsModalFormSelect}/>
      )}
    </div>
  );
}

export default ModalAdvice;
