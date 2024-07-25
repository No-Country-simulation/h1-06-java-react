import Buttons from "../../../../components/Buttons/Buttons";

function Modal() {
  return (
    <div id="modal">
      <div id="modal-container" className="modalContainer">
        <span>Estas seguro?</span>
        <div>
          <Buttons text="Cancelar"></Buttons>
          <Buttons text="Confirmar"></Buttons>
        </div>
      </div>
    </div>
  );
}

export default Modal;
