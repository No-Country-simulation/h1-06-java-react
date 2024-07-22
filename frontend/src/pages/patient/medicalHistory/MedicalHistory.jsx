import { useState } from "react";
import Titles from "../../../components/Titles/Titles";
import HistoryTitleSelector from "./components/HistoryTitleSelector";
import MedicalRecords from "./components/MedicalRecords";
import "./MedicalHistory.css";

function MedicalHistory() {
  const [dateSelected, setDateSelected] = useState(null);
  return (
    <div id="medicalHistory">
      <div id="medicalHistory-container">
        <div id="medicalHistory-header">
          <Titles title="Historia Clinica"></Titles>
        </div>
        <HistoryTitleSelector setDateSelected={setDateSelected} />
        <MedicalRecords dateSelected={dateSelected} />
      </div>
    </div>
  );
}

export default MedicalHistory;
