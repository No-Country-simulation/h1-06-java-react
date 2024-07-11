/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
import './DoctorForm.css'
import { Link } from 'react-router-dom'
import Buttons from '../../components/Buttons/Buttons'

function DoctorForm({ registerDoctor, setRegisterDoctor }) {
  const handleSpecialtyChange = (e) => {
    setRegisterDoctor({
      ...registerDoctor,
      specialty: e.target.value,
    })
  }

  return (
    <form className="doctorForm">
      <div className="doctorForm__container flex-column-center">
        <div className="flex-column">
          <label>Nombre</label>
          <input
            type="text"
            required
            value={registerDoctor.name}
            onChange={(e) =>
              setRegisterDoctor({
                ...registerDoctor,
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
            required
            value={registerDoctor.lastName}
            onChange={(e) =>
              setRegisterDoctor({
                ...registerDoctor,
                lastName: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Matrícula Profesional</label>
          <input
            type="number"
            required
            value={registerDoctor.professionalRegistration}
            onChange={(e) =>
              setRegisterDoctor({
                ...registerDoctor,
                professionalRegistration: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Seleccione su especialidad</label>
          <select
            className="inputLayout"
            value={registerDoctor.specialty}
            onChange={handleSpecialtyChange}
          >
            <option className="interFont" value="">
              Selecciones su especialidad
            </option>
            {registerDoctor.specialties &&
              registerDoctor.specialties.map((specialty) => (
                <option key={specialty} value={specialty}>
                  {specialty}
                </option>
              ))}
          </select>
        </div>
        <div className="flex-column">
          <label>Email</label>
          <input
            type="email"
            required
            value={registerDoctor.email}
            onChange={(e) =>
              setRegisterDoctor({
                ...registerDoctor,
                email: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Contraseña</label>
          <input
            type="password"
            required
            value={registerDoctor.password}
            onChange={(e) =>
              setRegisterDoctor({
                ...registerDoctor,
                password: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <div>
            <Link to={'#'} target="_blank">
              <input
                type="checkbox"
                name=""
                id=""
                required
                value={registerDoctor.readTreatment}
                onChange={(e) =>
                  setRegisterDoctor({
                    ...registerDoctor,
                    readTreatment: e.target.value,
                  })
                }
              />
            </Link>
            Leer Consentimiento de Transplante cruzado
          </div>
          <div>
            <Link to={'#'} target="_blank">
              <input
                type="checkbox"
                name=""
                id=""
                required
                value={registerDoctor.confirmPersonalData}
                onChange={(e) =>
                  setRegisterDoctor({
                    ...registerDoctor,
                    confirmPersonalData: e.target.value,
                  })
                }
              />
            </Link>
            Tratamiento de Datos Personales
          </div>
        </div>
        <div>
          <Buttons variant="primary" label={'Crear Cuenta'}></Buttons>
        </div>
      </div>
      <div>
        <div className="flex-row-evenly marginTop-20">
          <span>Ya tiene una cuenta?</span>
          <Link to="/login">Inicie Sesión</Link>
        </div>
      </div>
    </form>
  )
}

export default DoctorForm
