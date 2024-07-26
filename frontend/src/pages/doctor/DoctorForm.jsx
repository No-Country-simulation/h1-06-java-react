/* eslint-disable react/prop-types */

import './DoctorForm.css'
import { Link } from 'react-router-dom'
import Buttons from '../../components/Buttons/Buttons'
import { useState } from 'react'
import ModalRegisterSuccess from '../patient/components/ModalRegisterSuccess'
import { RegisterDoctor } from '../../services/Doctor/RegisterDoctor'

const licensePlace = {
  MATRICULA_NACIONAL: 'MATRICULA_NACIONAL',
  MATRICULA_DE_BUENOS_AIRES: 'MATRICULA_DE_BUENOS_AIRES',
  MATRICULA_DE_CATAMARCA: 'MATRICULA_DE_CATAMARCA',
  MATRICULA_DE_CHACO: 'MATRICULA_DE_CHACO',
  MATRICULA_DE_CHUBUT: 'MATRICULA_DE_CHUBUT',
  MATRICULA_DE_CORDOBA: 'MATRICULA_DE_CORDOBA',
  MATRICULA_DE_CORRIENTES: 'MATRICULA_DE_CORRIENTES',
  MATRICULA_DE_ENTRE_RIOS: 'MATRICULA_DE_ENTRE_RIOS',
  MATRICULA_DE_FORMOSA: 'MATRICULA_DE_FORMOSA',
  MATRICULA_DE_JUJUY: 'MATRICULA_DE_JUJUY',
  MATRICULA_DE_LA_PAMPA: 'MATRICULA_DE_LA_PAMPA',
  MATRICULA_DE_LA_RIOJA: 'MATRICULA_DE_LA_RIOJA',
  MATRICULA_DE_MENDOZA: 'MATRICULA_DE_MENDOZA',
  MATRICULA_DE_MISIONES: 'MATRICULA_DE_MISIONES',
  MATRICULA_DE_NEUQUEN: 'MATRICULA_DE_NEUQUEN',
  MATRICULA_DE_RIO_NEGRO: 'MATRICULA_DE_RIO_NEGRO',
  MATRICULA_DE_SALTA: 'MATRICULA_DE_SALTA',
  MATRICULA_DE_SAN_JUAN: 'MATRICULA_DE_SAN_JUAN',
  MATRICULA_DE_SAN_LUIS: 'MATRICULA_DE_SAN_LUIS',
  MATRICULA_DE_SANTA_CRUZ: 'MATRICULA_DE_SANTA_CRUZ',
  MATRICULA_DE_SANTA_FE: 'MATRICULA_DE_SANTA_FE',
  MATRICULA_DE_SANTIAGO_DEL_ESTERO: 'MATRICULA_DE_SANTIAGO_DEL_ESTERO',
  MATRICULA_DE_TIERRA_DEL_FUEGO: 'MATRICULA_DE_TIERRA_DEL_FUEGO',
  MATRICULA_DE_TUCUMAN: 'MATRICULA_DE_TUCUMAN',
  MATRICULA_DE_CIUDAD_AUTONOMA_DE_BUENOS_AIRES:
    'MATRICULA_DE_CIUDAD_AUTONOMA_DE_BUENOS_AIRES',
}

function DoctorForm({ registerDoctor, setRegisterDoctor }) {
  const [isGenderSelectionShown, setIsGenderSelectionShown] = useState(false)
  const [registerSuccessModalVisible, setRegisterSuccessModalVisible] =
    useState(false)
  const [modalContent, setModalContent] = useState({
    message: null,
    linkTo: null,
    buttonLabel: null,
  })

  const handleSpecialtyChange = (e) => {
    setRegisterDoctor({
      ...registerDoctor,
      specialty: e.target.value,
    })
  }

  const handleLicenseChange = (e) => {
    setRegisterDoctor({
      ...registerDoctor,
      licensePlace: e.target.value,
    })
  }

  const handleCheckboxChange = (e) => {
    const { name, checked } = e.target
    setRegisterDoctor({
      ...registerDoctor,
      [name]: checked,
    })
  }

  async function submitHandler(e) {
    e.preventDefault()
    try {
      const response = await RegisterDoctor(registerDoctor)
      if (response && response.id) {
        setRegisterSuccessModalVisible(true)
        setModalContent({
          message: 'Registro exitoso',
          linkTo: '/login',
          buttonLabel: 'Continuar',
        })
      } else {
        setRegisterSuccessModalVisible(true)
        setModalContent({
          message: `${response.errorMessages[0]}`,
          linkTo: '',
          buttonLabel: 'Volver',
        })
      }
    } catch (error) {
      console.error('Failed to register doctor:', error)
      console.log(error)
    }
  }

  console.log(registerDoctor)

  return (
    <div>
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
                onClick={() =>
                  setIsGenderSelectionShown(!isGenderSelectionShown)
                }
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
            <select
              className="inputLayout"
              value={registerDoctor.licensePlace}
              onChange={handleLicenseChange}
            >
              <option className="interFont" value="">
                Seleccione su matrícula profesional
              </option>
              {Object.entries(licensePlace).map(([key, value]) => (
                <option key={key} value={value}>
                  {value.replace(/_/g, ' ')}
                </option>
              ))}
            </select>
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
                Seleccione su especialidad
              </option>
              {registerDoctor.specialties &&
                registerDoctor.specialties.map((specialty) => (
                  <option key={specialty} value={specialty}>
                    {specialty.replace(/_/g, ' ')}
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
                  name="morning"
                  checked={registerDoctor.morning}
                  onChange={handleCheckboxChange}
                />
                Mañana
              </label>
              <label htmlFor="afternoon">
                <input
                  id="afternoon"
                  type="checkbox"
                  name="afternoon"
                  checked={registerDoctor.afternoon}
                  onChange={handleCheckboxChange}
                />
                Mediodía
              </label>
              <label htmlFor="evening">
                <input
                  id="evening"
                  type="checkbox"
                  name="evening"
                  checked={registerDoctor.evening}
                  onChange={handleCheckboxChange}
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
      {registerSuccessModalVisible ? (
        <ModalRegisterSuccess
          message={modalContent.message}
          buttonLabel={modalContent.buttonLabel}
          linkTo={modalContent.linkTo}
          onClick={() => setRegisterSuccessModalVisible(false)}
        ></ModalRegisterSuccess>
      ) : null}
    </div>
  )
}

export default DoctorForm
