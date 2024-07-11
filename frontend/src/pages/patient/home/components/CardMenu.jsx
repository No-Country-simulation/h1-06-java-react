/* eslint-disable react/prop-types */
import { Link } from "react-router-dom";

function CardMenu({ ...props }) {
  return (
    <Link to={props.url} className="cardMenu">
      <div id="cardContainer">
        <h2>{props.title}</h2>
      </div>
    </Link>
  );
}

export default CardMenu;
