import Buttons from "../../../../../components/Buttons/Buttons";

function EditDataForm() {
  return (
    <div id="edit-personal-data">
      <div id="personal-data-container" className="flex-column">
        <div id="personal-data-header" className="flex-row">
          <div id="personal-data-title">
            <h2>Editar Datos personales</h2>
          </div>
        </div>
        <form id="personal-data-content" className="flex-column">
          <div className="flex-column">
            <span className="personalDataTitle">Nombre completo</span>
            <input
              className="personalDataInput"
              type="text"
              defaultValue="Pepito Flores"
            />
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Fecha de nacimiento</span>
            <input
              className="personalDataInput"
              type="text"
              defaultValue="02/09/2000"
            />
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Sexo</span>
            <input
              className="personalDataInput"
              type="text"
              defaultValue="Masculino"
            />
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Email</span>
            <input
              className="personalDataInput"
              type="text"
              defaultValue="fulanito@fulanito"
            />
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Contraseña</span>
            <input className="personalDataInput" type="text" />
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Teléfono</span>
            <input
              className="personalDataInput"
              type="text"
              defaultValue="+1156546464654"
            />
          </div>
          <Buttons variant="primary" label={"Guardar Cambios"}></Buttons>
        </form>
      </div>
    </div>
  );
}

export default EditDataForm;
