import { Link } from "react-router-dom";

function MedicalData() {
  return (
    <div id="medical-data">
      <div id="medical-data-container">
        <div id="medical-data-header" className="flex-row">
          <div id="medical-data-title">
            <h2>Informacion Medica</h2>
          </div>
          <div id="medical-data-edit"></div>
        </div>
        <div id="medical-data-content" className="flex-column">
          <Link to={"/patient/prepaga"}>Prepaga</Link>
          <Link to={"/patient/help"}>Historia clinica</Link>
          <Link to={"/"}>Seguimientos</Link>
        </div>
      </div>
    </div>
  );
}

export default MedicalData;
