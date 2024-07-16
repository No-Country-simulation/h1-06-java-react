/* eslint-disable react/prop-types */
import React from "react";
import { Link } from "react-router-dom";
import Notifications from "../../../../components/Notifications/Notifications";

function CardNotification({ ...props }) {
  return (
    <>
      <Notifications
        type={props.type}
        title={props.title}
        time={props.time}
      ></Notifications>
    </>
  );
}

export default CardNotification;
