function PersonalData() {
  return (
    <div id="personal-data">
      <div id="personal-data-container" className="flex-column">
        <div id="personal-data-header" className="flex-row">
          <div id="personal-data-title">
            <h2>Datos personales</h2>
          </div>
          <div id="personal-data-edit">
            <button>Editar</button>
          </div>
        </div>
        <div id="personal-data-content" className="flex-column">
          <div className="flex-column">
            <span className="personalDataTitle">Nombre completo</span>
            <span className="personalDataContent">Pepito Flores</span>
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Fecha de nacimiento</span>
            <span className="personalDataContent">02/09/2000</span>
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Sexo</span>
            <span className="personalDataContent">Masculino</span>
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Email</span>
            <span className="personalDataContent">fulanito@fulanito</span>
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Contraseña</span>
            <span className="personalDataContent">*************</span>
          </div>
          <div className="flex-column">
            <span className="personalDataTitle">Teléfono</span>
            <span className="personalDataContent">+1156546464654</span>
          </div>
        </div>
      </div>
    </div>
  );
}

export default PersonalData;
