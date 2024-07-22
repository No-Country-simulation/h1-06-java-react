//import Calendar from "../../../../../public/assets/icons/calendarB.svg";
function HistoryTitleSelector(...props) {
  return (
    <div id="historyTitleSelector">
      <div id="historyTitleSelector-container">
        <h2>Historial</h2>
       <input type="date" defaultValue={"Seleccionar fecha"} value={"Seleccionar fecha"}/>
      </div>
    </div>
  );
}

export default HistoryTitleSelector;
