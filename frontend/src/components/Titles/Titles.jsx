/* eslint-disable react/prop-types */
import { Link } from "react-router-dom";
import chevronLeft from "../../../public/assets/icons/chevronLeft.svg";
import "./Titles.css";

function Titles({ title, url }) {
  return (
    <div className="profile-header">
      <Link to={`${url}`} className="profile-header-back">
        <img src={chevronLeft} />
      </Link>
      <div>
        <h2>{title}</h2>
      </div>
      <div></div>
    </div>
  );
}

export default Titles;
