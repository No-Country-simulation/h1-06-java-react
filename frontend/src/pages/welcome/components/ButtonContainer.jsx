import Buttons from "../../../components/Buttons";

function ButtonContainer() {
  return (
    <div id="button-container">
      <div id="welcome-buttons" className="flex-column-center gapButtons">
        <Buttons label="Iniciar Sesión" variant="primary" />
        <Buttons label="Regístrese" variant="secondary" />
      </div>
    </div>
  );
}

export default ButtonContainer;
