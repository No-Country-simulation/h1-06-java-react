import Buttons from "../../../../../components/Buttons/Buttons";
import { useUserLogin } from "../../../../../store/UserLogin";

function EditDataForm() {
  const { user } = useUserLogin();
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
            <span className="personalDataTitle">Nombreo</span>
            <input
              className="personalDataInput"
              type="text"
              defaultValue={user.name}
            />
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Apellido</span>
            <input
              className="personalDataInput"
              type="text"
              defaultValue={user.surname}
            />
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Fecha de nacimiento</span>
            <input
              className="personalDataInput"
              type="text"
              defaultValue={user.dateOfBirth}
            />
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Sexo</span>
            <input
              className="personalDataInput"
              type="text"
              defaultValue={user.gender}
            />
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Email</span>
            <input
              className="personalDataInput"
              type="text"
              defaultValue={user.email}
            />
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Contraseña</span>
            <input className="personalDataInput" type="text" />
          </div>
          <Buttons variant="primary" label={"Guardar Cambios"}></Buttons>
        </form>
      </div>
    </div>
  );
}

export default EditDataForm;
