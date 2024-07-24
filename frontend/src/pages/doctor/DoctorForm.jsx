/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
import './DoctorForm.css'
import { Link } from 'react-router-dom'
import Buttons from '../../components/Buttons/Buttons'
import { RegisterDoctor } from '../../services/Doctor/RegisterDoctor'
import { useState } from 'react'
import checkBirthDate from '../../hooks/checkBirthDate'

function DoctorForm({ registerDoctor, setRegisterDoctor }) {
  const [isGenderSelectionShown, setIsGenderSelectionShown] = useState(false)

  const handleSpecialtyChange = (e) => {
    setRegisterDoctor({
      ...registerDoctor,
      specialty: e.target.value,
    })
  }

  function submitHandler(e) {
    e.preventDefault()
    RegisterDoctor(registerDoctor)
  }

  console.log(registerDoctor)

  return (
    <form className="doctorForm" onSubmit={submitHandler}>
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
            value={registerDoctor.surname}
            onChange={(e) =>
              setRegisterDoctor({
                ...registerDoctor,
                surname: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Sexo</label>
          <div className="inputLayout">
            <div
              type="text"
              value={registerDoctor.gender}
              className="gender-select"
              onClick={() => setIsGenderSelectionShown(!isGenderSelectionShown)}
            >
              {registerDoctor.gender
                ? registerDoctor.gender
                : 'Selecciona tu sexo'}
            </div>
          </div>
          {isGenderSelectionShown && (
            <div id="gender-box">
              <div className="gender-selection">
                <button
                  value="MASCULINO"
                  onClick={(e) => {
                    setRegisterDoctor({
                      ...registerDoctor,
                      gender: e.target.value,
                    }),
                      setIsGenderSelectionShown(false)
                  }}
                >
                  Masculino
                </button>
                <button
                  value="FEMENINO"
                  onClick={(e) => {
                    setRegisterDoctor({
                      ...registerDoctor,
                      gender: e.target.value,
                    }),
                      setIsGenderSelectionShown(false)
                  }}
                >
                  Femenino
                </button>{' '}
                <button
                  value="OTRO"
                  onClick={(e) => {
                    setRegisterDoctor({
                      ...registerDoctor,
                      gender: e.target.value,
                    }),
                      setIsGenderSelectionShown(false)
                  }}
                >
                  Otro
                </button>
              </div>
            </div>
          )}
        </div>
        <div className="flex-column">
          <label>DNI</label>
          <input
            type="number"
            required
            value={registerDoctor.dni}
            onChange={(e) =>
              setRegisterDoctor({
                ...registerDoctor,
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
            value={registerDoctor.dateOfBirth}
            onChange={(e) =>
              setRegisterDoctor({
                ...registerDoctor,
                dateOfBirth: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Dirección</label>
          <input
            type="text"
            required
            value={registerDoctor.address}
            onChange={(e) =>
              setRegisterDoctor({
                ...registerDoctor,
                address: e.target.value,
              })
            }
            className="inputLayout"
          />
        </div>
        <div className="flex-column">
          <label>Lugar de la matrícula Profesional</label>
          <input
            type="text"
            required
            value={registerDoctor.licensePlace}
            onChange={(e) =>
              setRegisterDoctor({
                ...registerDoctor,
                licensePlace: e.target.value,
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
            value={registerDoctor.medicalLicense}
            onChange={(e) =>
              setRegisterDoctor({
                ...registerDoctor,
                medicalLicense: e.target.value,
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
          <label>Seleccione su rango de atención</label>
          <div className="contentDayRange">
            <label htmlFor="morning">
              <input
                id="morning"
                type="checkbox"
                value={registerDoctor.morning}
                onChange={(e) =>
                  setRegisterDoctor({
                    ...registerDoctor,
                    morning: e.target.value,
                  })
                }
              />
              Mañana
            </label>
            <label htmlFor="afternoon">
              <input
                id="afternoon"
                type="checkbox"
                value={registerDoctor.afternoon}
                onChange={(e) =>
                  setRegisterDoctor({
                    ...registerDoctor,
                    afternoon: e.target.value,
                  })
                }
              />
              Mediodía
            </label>
            <label htmlFor="evening">
              <input
                id="evening"
                type="checkbox"
                value={registerDoctor.evening}
                onChange={(e) =>
                  setRegisterDoctor({
                    ...registerDoctor,
                    evening: e.target.value,
                  })
                }
              />
              Tarde
            </label>
          </div>
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
