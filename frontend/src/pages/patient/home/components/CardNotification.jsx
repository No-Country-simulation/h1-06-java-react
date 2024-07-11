/* eslint-disable react/prop-types */
import React from "react";
import { Link } from "react-router-dom";

function CardNotification({ ...props }) {
  return (
    <Link to={"/"} className="cardNotification">
      <div>
        <div className="patientNotificationType">
          <p>{props.type}</p>
        </div>
        <div className="patientNotificationText">
          <h3>{props.title}</h3>
          <p>{props.time}</p>
        </div>
      </div>
    </Link>
  );
}

export default CardNotification;
