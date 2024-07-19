import './Cancel.css'

function Cancel() {
  return (
    <div className="contentCancel">
      <h4>Cancelación de turnos</h4>
      <div className="contentLabelAndInput">
        <label htmlFor="genre" className="labelDataPatient">
          Turno
        </label>
        <select name="genre" id="genre" className="inputDataPatient">
          <option className="interFont" value="">
            Selecciones su turno
          </option>
          <option value="femenino">Mañana</option>
          <option value="masculino">Tarde</option>
          <option value="otro">Noche</option>
        </select>
      </div>
      <div className="contentLabelAndInput">
        <label htmlFor="date" className="labelDataPatient">
          Día/Semana
        </label>
        <input type="date" name="date" id="date" className="inputDataPatient" />
      </div>
      <button className="btnHistoryPatient">Confirmar</button>
    </div>
  )
}

export default Cancel
