/* eslint-disable react/prop-types */
import { useState } from "react";
import Buttons from "../../../components/Buttons/Buttons";
import arrowLeft from "../../../../public/assets/icons/chevronLeft.svg";
import { RegisterRelative } from "../../../services/Patient/RegisterRelative";

function ModalForm({ ...props }) {
  const [registerForm, setRegisterForm] = useState({
    name: "",
    email: "",
    surname: "",
    dni: "",
    dateOfBirth: "",
    gender: "",
    password: "",
    confirmPersonalData: false,
    tutor: false,
    assistedEmail: "",
    address: "",
  });

  const registerRelativeHandler = async (e) => {
    e.preventDefault();
    const response = await RegisterRelative(registerForm);
    if (response) {
      props.setIsModalFormSelect(false);
    }
  };

  console.log(registerForm);
  return (
    <div id="tutor-form">
      <div onClick={() => props.setIsModalFormSelect(false)}>
        <img src={arrowLeft} />
      </div>
      <form
        id="tutor-form-container"
        className="flex-column-center"
        onSubmit={registerRelativeHandler}
      >
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
            value={registerForm.surname}
            onChange={(e) =>
              setRegisterForm({
                ...registerForm,
                surname: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Municipio</label>
          <input
            type="text"
            value={registerForm.address}
            onChange={(e) =>
              setRegisterForm({
                ...registerForm,
                address: e.target.value,
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
            value={registerForm.dateOfBirth}
            className="inputLayout"
            onChange={(e) =>
              setRegisterForm({
                ...registerForm,
                dateOfBirth: e.target.value,
              })
            }
          />
        </div>
        <div className="flex-column">
          <label>Sexo</label>
          <select
            className="inputLayout"
            onChange={(e) =>
              setRegisterForm({ ...registerForm, gender: e.target.value })
            }
          >
            <option value="">Selecciones su sexo</option>
            <option value="MASCULINO">Masculino</option>
            <option value="FEMENINO">Femenino</option>
          </select>
        </div>
        <div className="flex-column">
          <label>Email</label>
          <input
            type="email"
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
        <div className="flex-column">
          <label>Mail del paciente</label>
          <input
            type="email"
            value={registerForm.assistedEmail}
            onChange={(e) =>
              setRegisterForm({
                ...registerForm,
                assistedEmail: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div>
          <Buttons
            variant="primary"
            label={"Confirmar"}
            type="submit"
          ></Buttons>
        </div>
      </form>
    </div>
  );
}

export default ModalForm;
