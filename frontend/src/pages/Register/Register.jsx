import { useEffect, useState } from 'react'
import PatientForm from '../patient/PatientForm'
import './Register.css'
import DoctorForm from '../doctor/DoctorForm'
import axios from 'axios'

function Register() {
  const [profileSelection, setProfileSelection] = useState(undefined)
  const [registerForm, setRegisterForm] = useState({
    name: '',
    surname: '',
    dni: '',
    email: '',
    password: '',
    dateOfBirth: '',
    gender: '',
    bloodType: '',
    healthcareProviderId: '0',
    socialSecurityNumber: '0',
    address: '',
    isDonor: false,
    confirmPersonalData: false,
    tutor: false,
  })

  const [registerDoctor, setRegisterDoctor] = useState({
    name: '',
    surname: '',
    gender: '',
    dni: '',
    dateOfBirth: '',
    address: '',
    licensePlace: '',
    medicalLicense: '',
    specialty: '', //guarda la propiedad seleccionada
    specialties: [],
    morning: false,
    afternoon: false,
    evening: false,
    email: '',
    password: '',
    readTreatment: false,
    confirmPersonalData: false,
  })

  const handleProfileChange = (e) => {
    setProfileSelection(e.target.value)
  }

  useEffect(() => {
    axios
      .get('http://localhost:7082/api/v1/doctor/specialties')
      .then((response) => {
        const specialties = response.data
        setRegisterDoctor((prevState) => ({
          ...prevState,
          specialties,
        }))
      })
      .catch((error) => {
        console.error('Error fetching specialties:', error)
      })
  }, [])

  return (
    <div id="register">
      <div id="register-container" className="interFont">
        <div id="register-title">
          <h1 className="just-another-hand-regular">Regístro</h1>
        </div>
        <div id="profile-selection">
          <div>
            <label>Seleccione su perfil</label>
            <ul>
              <select className="inputLayout" onChange={handleProfileChange}>
                <option className="interFont" value="">
                  Selecciones su perfil
                </option>
                <option value="paciente">Paciente</option>
                <option value="familiar-paciente">Familiar Paciente</option>
                <option value="medico">Medico</option>
                <option value="centro-transplante">
                  Centro de Transplante
                </option>
                <option value="terapia-intensiva">Terapia Intensiva</option>
                <option value="asiociacion">Asociacion/ Fundacion/ ONG</option>
                <option value="incucai">INCUCAI</option>
                <option value="casa-justina">Casa Justina</option>
                <option value="farmacia">Farmacia</option>
              </select>
            </ul>
          </div>
        </div>

        {profileSelection === 'paciente' ? (
          <PatientForm
            profileSelection={profileSelection}
            registerForm={registerForm}
            setRegisterForm={setRegisterForm}
          />
        ) : profileSelection === 'medico' ? (
          <DoctorForm
            profileSelection={profileSelection}
            registerDoctor={registerDoctor}
            setRegisterDoctor={setRegisterDoctor}
          />
        ) : null}
      </div>
    </div>
  )
}

export default Register
