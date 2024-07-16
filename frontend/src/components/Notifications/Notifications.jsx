/* eslint-disable react/prop-types */
import "./Notifications.css";
import { Link } from "react-router-dom";

function Notifications({ type, title, time, url }) {
  return (
    <div id="patient-notifications-container">
      <div className="patient-notifications">
        <Link to={`${url}`} className="cardNotification">
          <div>
            <div className="patientNotificationType">
              <p>{type}</p>
            </div>
            <div className="patientNotificationText">
              <h3>{title}</h3>
              <p>{time}</p>
            </div>
          </div>
        </Link>
      </div>
    </div>
  );
}

export default Notifications;
