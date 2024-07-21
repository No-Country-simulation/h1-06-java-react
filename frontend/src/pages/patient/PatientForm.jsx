/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
import { useState } from "react";
import "./PatientForm.css";
import { Link } from "react-router-dom";
import checkBirthDate from "../../hooks/checkBirthDate";
import ModalAdvice from "./components/ModalAdvice";
import Buttons from "../../components/Buttons/Buttons";
import { RegisterPatient } from "../../services/Patient/RegisterPatient";
function PatientForm({ ...props }) {
  const [isGenderSelectionShown, setIsGenderSelectionShown] = useState(false);
  const [isBloodTypeShown, setIsBloodTypeShown] = useState(false);
  const [isShownModal, setIsShownModal] = useState(false);
  const [isModalFormSelect, setIsModalFormSelect] = useState(undefined);
  function birthDateHandler(e) {
    if (checkBirthDate(e.target.value)) {
      props.setRegisterForm({
        ...props.registerForm,
        dateOfBirth: e.target.value,
      });
    } else {
      setIsShownModal(true);
      return false;
    }
  }

  function submitHandler(e) {
    e.preventDefault();
    RegisterPatient(props.registerForm);
  }

  console.log(props.registerForm);
  return (
    <div id="patient-form">
      <form
        id="patient-form-container"
        className="flex-column-center"
        onSubmit={submitHandler}
      >
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
            value={props.registerForm.surname}
            onChange={(e) =>
              props.setRegisterForm({
                ...props.registerForm,
                surname: e.target.value,
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
            value={props.registerForm.dateOfBirth}
            onInput={birthDateHandler}
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Sexo</label>
          <div className="inputLayout">
            <div
              type="text"
              value={props.registerForm.gender}
              className="gender-select"
              onClick={() => setIsGenderSelectionShown(!isGenderSelectionShown)}
            >
              {props.registerForm.gender
                ? props.registerForm.gender
                : "Selecciona tu sexo"}
            </div>
          </div>
          {isGenderSelectionShown && (
            <div id="gender-box">
              <div className="gender-selection">
                <button
                  value="MASCULINO"
                  onClick={(e) => {
                    props.setRegisterForm({
                      ...props.registerForm,
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
                    props.setRegisterForm({
                      ...props.registerForm,
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
                    props.setRegisterForm({
                      ...props.registerForm,
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
          <label>address</label>
          <input
            type="text"
            value={props.registerForm.address}
            onChange={(e) =>
              props.setRegisterForm({
                ...props.registerForm,
                address: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Seleciona tu grupo sanguineo</label>
          <div className="inputLayout">
            <div
              type="text"
              value={props.registerForm.bloodType}
              className="gender-select"
              onClick={() => setIsBloodTypeShown(!isBloodTypeShown)}
            >
              {props.registerForm.bloodType
                ? props.registerForm.bloodType
                : "Selecciona tu grupo sanguineo"}
            </div>
          </div>
          {isBloodTypeShown && (
            <div id="bloodType-box">
              <div className="bloodType-selection">
                <button
                  value="A_POSITIVO"
                  onClick={(e) => {
                    props.setRegisterForm({
                      ...props.registerForm,
                      bloodType: e.target.value,
                    }),
                      setIsBloodTypeShown(false);
                  }}
                >
                  A+
                </button>
                <button
                  value="B_POSITIVO"
                  onClick={(e) => {
                    props.setRegisterForm({
                      ...props.registerForm,
                      bloodType: e.target.value,
                    }),
                      setIsBloodTypeShown(false);
                  }}
                >
                  B+
                </button>
                <button
                  value="AB_POSITIVO"
                  onClick={(e) => {
                    props.setRegisterForm({
                      ...props.registerForm,
                      bloodType: e.target.value,
                    }),
                      setIsBloodTypeShown(false);
                  }}
                >
                  AB+
                </button>
                <button
                  value="0_POSITIVO"
                  onClick={(e) => {
                    props.setRegisterForm({
                      ...props.registerForm,
                      bloodType: e.target.value,
                    }),
                      setIsBloodTypeShown(false);
                  }}
                >
                  0+
                </button>
                <button
                  value="A_NEGATIVO"
                  onClick={(e) => {
                    props.setRegisterForm({
                      ...props.registerForm,
                      bloodType: e.target.value,
                    }),
                      setIsBloodTypeShown(false);
                  }}
                >
                  A-
                </button>
                <button
                  value="_NEGATIVO"
                  onClick={(e) => {
                    props.setRegisterForm({
                      ...props.registerForm,
                      bloodType: e.target.value,
                    }),
                      setIsBloodTypeShown(false);
                  }}
                >
                  B-
                </button>
                <button
                  value="AB_NEGATIVO"
                  onClick={(e) => {
                    props.setRegisterForm({
                      ...props.registerForm,
                      bloodType: e.target.value,
                    }),
                      setIsBloodTypeShown(false);
                  }}
                >
                  AB-
                </button>
                <button
                  value="0_NEGATIVO"
                  onClick={(e) => {
                    props.setRegisterForm({
                      ...props.registerForm,
                      bloodType: e.target.value,
                    }),
                      setIsBloodTypeShown(false);
                  }}
                >
                  0-
                </button>
              </div>
            </div>
          )}
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
            Estás diligenciando para alguien más?
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
          <Buttons
            variant="primary"
            label={"Crear Cuenta"}
            type="submit"
          ></Buttons>
        </div>
      </form>
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
