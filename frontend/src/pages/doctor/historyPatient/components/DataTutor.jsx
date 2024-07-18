import arrowDown from '/public/assets/icons/arrowDown.svg'
import edit from '/public/assets/icons/edit.svg'

function DataTutor() {
  return (
    <section className="contentDataPatient">
      <section className="contentTitleDataPatient">
        <div className="contentTextDataPatient">
          <h4 className="titleDataPatient">Datos del tutor</h4>
          <img src={edit} className="editDataPatient" />
        </div>
        <img src={arrowDown} className="arrowDownDataPatient" />
      </section>
      <form className="contentFormDataPatient">
        <div className="contentLabelAndInput">
          <label htmlFor="name" className="labelDataPatient">
            Nombre
          </label>
          <input
            type="text"
            placeholder="name"
            id="name"
            className="inputDataPatient"
          />
        </div>
        <div className="contentLabelAndInput">
          <label htmlFor="lastname" className="labelDataPatient">
            Apellido
          </label>
          <input
            type="text"
            placeholder="apellido"
            id="lastname"
            className="inputDataPatient"
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
          />
        </div>
        <div className="contentLabelAndInput">
          <label htmlFor="date" className="labelDataPatient">
            Fecha de nacimiento
          </label>
          <input type="date" id="date" className="inputDataPatient" />
        </div>
        <div className="contentLabelAndInput"></div>
        <div className="contentLabelAndInput">
          <label htmlFor="genre" className="labelDataPatient">
            Género
          </label>
          <select name="genre" id="genre" className="inputDataPatient">
            <option className="interFont" value="">
              Selecciones su género
            </option>
            <option value="femenino">Femenino</option>
            <option value="masculino">Masculino</option>
            <option value="otro">Otro</option>
          </select>
        </div>
        <div className="contentLabelAndInput">
          <label htmlFor="phone" className="labelDataPatient">
            Teléfono
          </label>
          <input
            type="number"
            placeholder="teléfono"
            id="phone"
            className="inputDataPatient"
          />
        </div>
        <div className="contentLabelAndInput">
          <label htmlFor="email" className="labelDataPatient">
            Email
          </label>
          <input
            type="email"
            placeholder="email"
            id="email"
            className="inputDataPatient"
          />
        </div>
        <div className="contentLabelAndInput">
          <label htmlFor="address" className="labelDataPatient">
            Dirección
          </label>
          <input
            type="text"
            placeholder="dirección"
            id="address"
            className="inputDataPatient"
          />
        </div>
        <button className="btnDataPatient">
          Crear o actualizar historia clínica
        </button>
      </form>
    </section>
  )
}

export default DataTutor
