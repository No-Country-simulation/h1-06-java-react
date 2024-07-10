/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
import { useState } from "react";
import "./PatientForm.css";
import { Link } from "react-router-dom";
import checkBirthDate from "../../hooks/checkBirthDate";
import ModalAdvice from "./components/ModalAdvice";
import Buttons from "../../components/Buttons/Buttons";
function PatientForm({ ...props }) {
  function birthDateHandler(e) {
    if (checkBirthDate(e.target.value)) {
      props.setRegisterForm({
        ...props.registerForm,
        birthDate: e.target.value,
      });
    } else {
      setIsShownModal(true);
      return false;
    }
  }
  const [isShownModal, setIsShownModal] = useState(false);
  const [isModalFormSelect, setIsModalFormSelect] = useState(undefined);
  return (
    <div id="patient-form">
      <div id="patient-form-container" className="flex-column-center">
        <div className="flex-column">
          <label>Nombre</label>
          <input
            type="text"
            value={props.registerForm.name}
            onChange={(e) =>
              props.setRegisterForm({
                ...props.registerForm,
                name: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Apellido</label>
          <input
            type="text"
            value={props.registerForm.lastName}
            onChange={(e) =>
              props.setRegisterForm({
                ...props.registerForm,
                lastName: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>DNI</label>
          <input
            type="text"
            value={props.registerForm.dni}
            onChange={(e) =>
              props.setRegisterForm({
                ...props.registerForm,
                dni: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        {birthDateHandler && (
          <ModalAdvice
            isShownModal={isShownModal}
            setIsShownModal={setIsShownModal}
            isModalFormSelect={isModalFormSelect}
            setIsModalFormSelect={setIsModalFormSelect}
          />
        )}
        <div className="flex-column">
          <label>Fecha de Nacimiento</label>
          <input
            type="date"
            value={props.registerForm.birthDate}
            onInput={birthDateHandler}
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Sexo</label>
          <input
            type="text"
            value={props.registerForm.gender}
            onChange={(e) =>
              props.setRegisterForm({
                ...props.registerForm,
                gender: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Email</label>
          <input
            type="text"
            value={props.registerForm.email}
            onChange={(e) =>
              props.setRegisterForm({
                ...props.registerForm,
                email: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Contraseña</label>
          <input
            type="text"
            value={props.registerForm.password}
            onChange={(e) =>
              props.setRegisterForm({
                ...props.registerForm,
                password: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div id="checkbox-questions" className="flex-column">
          <div>
            <input
              type="checkbox"
              name=""
              id=""
              value={props.registerForm.tutor}
              onChange={(e) =>
                props.setRegisterForm({
                  ...props.registerForm,
                  tutor: e.target.value,
                })
              }
              onClick={() => setIsShownModal(!isShownModal)}
              {...(props.registerForm.tutor ? "checked" : "")}
            />
            Estás diligenciando para laguien más?
          </div>
          <div>
            <Link to={"#"} target="_blank">
              <input
                type="checkbox"
                name=""
                id=""
                value={props.registerForm.confirmPersonalData}
                onChange={(e) =>
                  props.setRegisterForm({
                    ...props.registerForm,
                    confirmPersonalData: e.target.value,
                  })
                }
              />
            </Link>
            Tratamiento de Datos Personales
          </div>
        </div>
        <div>
          <Buttons variant="primary" label={"Crear Cuenta"}></Buttons>
        </div>
      </div>
      <div>
        <div className="flex-row-evenly marginTop-20">
          <span>Ya tiene una cuenta?</span>
          <Link to="/login">Inicie Sesión</Link>
        </div>
      </div>
    </div>
  );
}

export default PatientForm;
