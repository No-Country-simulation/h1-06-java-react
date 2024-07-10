/* eslint-disable react/prop-types */
import { useState } from "react";
import Buttons from "../../../components/Buttons/Buttons";
import arrowLeft from "../../../../public/assets/icons/chevronLeft.svg";

function ModalForm({ ...props }) {
  const [registerForm, setRegisterForm] = useState({
    name: "",
    email: "",
    lastName: "",
    dni: "",
    birthDate: "",
    gender: "",
    password: "",
    confirmPersonalData: false,
    tutor: false,
  });
  return (
    <div id="tutor-form">
      <div onClick={() => props.setIsModalFormSelect(false)}>
        <img src={arrowLeft} />
      </div>
      <div id="tutor-form-container" className="flex-column-center">
        <div className="flex-column">
          <label>Nombre</label>
          <input
            type="text"
            value={registerForm.name}
            onChange={(e) =>
              setRegisterForm({
                ...registerForm,
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
            value={registerForm.lastName}
            onChange={(e) =>
              setRegisterForm({
                ...registerForm,
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
            value={registerForm.dni}
            onChange={(e) =>
              setRegisterForm({
                ...registerForm,
                dni: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Fecha de Nacimiento</label>
          <input
            type="date"
            value={registerForm.birthDate}
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Sexo</label>
          <input
            type="text"
            value={registerForm.gender}
            onChange={(e) =>
              setRegisterForm({
                ...registerForm,
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
            value={registerForm.email}
            onChange={(e) =>
              setRegisterForm({
                ...registerForm,
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
            value={registerForm.password}
            onChange={(e) =>
              setRegisterForm({
                ...registerForm,
                password: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div>
          <Buttons variant="primary" label={"Confirmar"}></Buttons>
        </div>
      </div>
    </div>
  );
}

export default ModalForm;
