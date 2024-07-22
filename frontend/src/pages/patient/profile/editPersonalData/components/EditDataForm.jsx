import Buttons from "../../../../../components/Buttons/Buttons";
import { useUserLogin } from "../../../../../store/UserLogin";
import { UpdatePersonalData } from "../../../../../services/Patient/UpdatePersonalData";
import { useState } from "react";

function EditDataForm() {
  const [isGenderSelectionShown, setIsGenderSelectionShown] = useState(false);
  const [userUpdate, setUserUpdate] = useState({
    name: "",
    surname: "",
    dni: "",
    password: "",
    dateOfBirth: "",
    gender: "",
  });
  const { user } = useUserLogin();

  const updateDataHandler = async (event) => {
    event.preventDefault();
    const response = await UpdatePersonalData(userUpdate, user);
    console.log(response);
  };
  return (
    <div id="edit-personal-data">
      <div id="personal-data-container" className="flex-column">
        <div id="personal-data-header" className="flex-row">
          <div id="personal-data-title">
            <h2>Editar Datos personales</h2>
          </div>
        </div>
        <form
          id="personal-data-content"
          className="flex-column"
          onSubmit={updateDataHandler}
        >
          <div className="flex-column">
            <span className="personalDataTitle">Nombreo</span>
            <input
              className="personalDataInput"
              type="text"
              defaultValue={user.name}
              onChange={(e) =>
                setUserUpdate({ ...userUpdate, name: e.target.value })
              }
            />
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Apellido</span>
            <input
              className="personalDataInput"
              type="text"
              defaultValue={user.surname}
              onChange={(e) =>
                setUserUpdate({ ...userUpdate, surname: e.target.value })
              }
            />
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Fecha de nacimiento</span>
            <input
              className="personalDataInput"
              type="date"
              defaultValue={user.dateOfBirth}
              onChange={(e) =>
                setUserUpdate({ ...userUpdate, dateOfBirth: e.target.value })
              }
            />
          </div>
          <div className="flex-column">
          <label>Sexo</label>
          <div className="inputLayout">
            <div
              type="text"
              value={user.gender}
              className="gender-select"
              onClick={() => setIsGenderSelectionShown(!isGenderSelectionShown)}
            >
              {user.gender
                ? userUpdate.gender
                : "Selecciona tu sexo"}
            </div>
          </div>
          {isGenderSelectionShown && (
            <div id="gender-box">
              <div className="gender-selection">
                <button
                  value="MASCULINO"
                  onClick={(e) => {
                    setUserUpdate({
                      ...userUpdate,
                      gender: e.target.value,
                    }),
                      setIsGenderSelectionShown(false);
                  }}
                >
                  Masculino
                </button>
                <button
                  value="FEMENINO"
                  onClick={(e) => {
                    setUserUpdate({
                      ...userUpdate,
                      gender: e.target.value,
                    }),
                      setIsGenderSelectionShown(false);
                  }}
                >
                  Femenino
                </button>{" "}
                <button
                  value="OTRO"
                  onClick={(e) => {
                    setUserUpdate({
                      ...userUpdate,
                      gender: e.target.value,
                    }),
                      setIsGenderSelectionShown(false);
                  }}
                >
                  Otro
                </button>
              </div>
            </div>
          )}
        </div>
          <div className="flex-column">
            <span className="personalDataTitle">Email</span>
            <input
              className="personalDataInput"
              type="text"
              defaultValue={user.email}
              onChange={(e) =>
                setUserUpdate({ ...userUpdate, email: e.target.value })
              }
            />
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Contraseña</span>
            <input
              className="personalDataInput"
              type="password"
              onChange={(e) =>
                setUserUpdate({ ...userUpdate, password: e.target.value })
              }
            />
          </div>
          <Buttons
            variant="primary"
            label={"Guardar Cambios"}
            type="submit"
          ></Buttons>
        </form>
      </div>
    </div>
  );
}

export default EditDataForm;
