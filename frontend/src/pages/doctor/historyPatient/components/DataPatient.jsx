/* eslint-disable react/prop-types */
import { useState } from 'react'
import arrowDown from '/public/assets/icons/arrowDown.svg'
import edit from '/public/assets/icons/edit.svg'

function DataPatient({ patientData }) {
  const [isFormVisible, setIsFormVisible] = useState(true)

  const toggleFormVisibility = () => {
    setIsFormVisible(!isFormVisible)
  }

  return (
    <section className="contentDataPatient">
      <section
        className="contentTitleDataPatient"
        onClick={toggleFormVisibility}
      >
        <div className="contentTextDataPatient">
          <h4 className="titleDataPatient">Datos del paciente</h4>
          <img src={edit} className="editDataPatient" alt="Editar" />
        </div>
        <img src={arrowDown} className="arrowDownDataPatient" alt="Expandir" />
      </section>
      {isFormVisible && (
        <form className="contentFormDataPatient">
          <div className="contentLabelAndInput">
            <label htmlFor="name" className="labelDataPatient">
              Nombre
            </label>
            <input
              type="text"
              placeholder="Nombre"
              id="name"
              className="inputDataPatient"
              value={patientData?.name || ''}
              readOnly
            />
          </div>
          <div className="contentLabelAndInput">
            <label htmlFor="lastname" className="labelDataPatient">
              Apellido
            </label>
            <input
              type="text"
              placeholder="Apellido"
              id="lastname"
              className="inputDataPatient"
              value={patientData?.surname || ''}
              readOnly
            />
          </div>
          <div className="contentLabelAndInput">
            <label htmlFor="dni" className="labelDataPatient">
              DNI
            </label>
            <input
              type="number"
              placeholder="DNI"
              id="dni"
              className="inputDataPatient"
              value={patientData?.dni || ''}
              readOnly
            />
          </div>
          <div className="contentLabelAndInput">
            <label htmlFor="date" className="labelDataPatient">
              Fecha de nacimiento
            </label>
            <input
              type="date"
              id="date"
              className="inputDataPatient"
              value={patientData?.dateOfBirth || ''}
              readOnly
            />
          </div>
          <div className="contentLabelAndInput"></div>
          <div className="contentLabelAndInput">
            <label htmlFor="genre" className="labelDataPatient">
              Género
            </label>
            <input
              type="text"
              name="genre"
              id="genre"
              className="inputDataPatient"
              value={patientData?.gender || ''}
              readOnly
            />
            {/* <select
              name="genre"
              id="genre"
              className="inputDataPatient"
              value={patientData?.gender || ''}
            >
              <option className="interFont" value="">
                Seleccione su género
              </option>
              <option value="femenino">Femenino</option>
              <option value="masculino">Masculino</option>
              <option value="otro">Otro</option>
            </select> */}
          </div>
          <div className="contentLabelAndInput">
            <label htmlFor="email" className="labelDataPatient">
              Email
            </label>
            <input
              type="email"
              placeholder="Email"
              id="email"
              className="inputDataPatient"
              value={patientData?.email || ''}
              readOnly
            />
          </div>
          <div className="contentLabelAndInput">
            <label htmlFor="address" className="labelDataPatient">
              Dirección
            </label>
            <input
              type="text"
              placeholder="Dirección"
              id="address"
              className="inputDataPatient"
              value={patientData?.address || ''}
              readOnly
            />
          </div>
          <button className="btnDataPatient">
            Crear o actualizar historia clínica
          </button>
        </form>
      )}
    </section>
  )
}

export default DataPatient
