/* eslint-disable react/prop-types */
//import Calendar from "../../../../../public/assets/icons/calendarB.svg";
function HistoryTitleSelector({...props}) {
  return (
    <div id="historyTitleSelector">
      <div id="historyTitleSelector-container">
        <h2>Historial</h2>
        {props.dateSelected === null ? (
          <div className="buttonInput" onClick={() => props.setDateSelected("date")}>Seleccionar Fecha</div>
        ) : (
          <input
            type="date"
            defaultValue={"Seleccionar fecha"}
            value={"Seleccionar fecha"}
          />
        )}
      </div>
    </div>
  );
}

export default HistoryTitleSelector;
