/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
function PatientForm({ ...props }) {
  return (
    <div id="patient-form">
      <div id="patient-form-container">
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
        <div className="flex-column">
          <label>Fecha de Nacimiento</label>
          <input
            type="text"
            value={props.registerForm.birthDate}
            onChange={(e) =>
              props.setRegisterForm({
                ...props.registerForm,
                birthDate: e.target.value,
              })
            }
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
      </div>
    </div>
  );
}

export default PatientForm;
