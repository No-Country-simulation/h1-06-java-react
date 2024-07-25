import arrowDown from '/public/assets/icons/arrowDown.svg'
import './ResultPatient.css'

function ResultPatient() {
  return (
    <section className="contentResultPatient">
      <h2 className="titleResultPatient">Paciente</h2>
      <section className="contentTagsResultPatient">
        <div className="TagResultPatient">
          <p className="titleTagResultPatient">Perfil</p>
        </div>
        <div className="TagResultPatient">
          <p className="titleTagResultPatient">Historia</p>
        </div>
        <div className="TagResultPatient">
          <p className="titleTagResultPatient">Exámenes</p>
        </div>
      </section>

      <section className="contentDataResultPatient">
        <section className="contentTitleDataPatient">
          <h4 className="titleDataPatient">Datos del tutor</h4>
          <img src={arrowDown} className="arrowDownDataPatient" />
        </section>
        <div className="contentLabelAndInput">
          <label htmlFor="patology" className="labelDataPatient">
            Diagnósgtico
          </label>
          <input type="date" className="inputDataPatient" />
        </div>
        <div className="contentLabelAndInput">
          <label htmlFor="patology" className="labelDataPatient">
            Diagnósgtico
          </label>
          <select name="genre" id="genre" className="inputDataPatient">
            <option className="interFont" value="">
              Patología
            </option>
            <option value="femenino">Femenino</option>
            <option value="masculino">Masculino</option>
            <option value="otro">Otro</option>
          </select>
        </div>
        <div className="contentLabelAndInput">
          <label htmlFor="patology" className="labelDataPatient">
            Tratamiento
          </label>
          <select name="genre" id="genre" className="inputDataPatient">
            <option className="interFont" value="">
              Patología
            </option>
            <option value="femenino">Femenino</option>
            <option value="masculino">Masculino</option>
            <option value="otro">Otro</option>
          </select>
        </div>
      </section>
    </section>
  )
}

export default ResultPatient
