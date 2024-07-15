/* eslint-disable react/prop-types */
import { Link } from "react-router-dom";
import ArrowRightIcon from "../../../../../public/assets/icons/arrow-right-circle.svg";
function CardMenu({ ...props }) {
  return (
    <Link to={props.cardInfo.url} className="cardMenu">
      <div id="cardContainer">
        <div id="iconMenuContainer">
          <img src={props.cardInfo.icon} className="menu-icon" />
        </div>
        <div className="cardMenu-titleCard">
          <h2>{props.cardInfo.title}</h2>
        </div>
        <div id="cardData-button-content">
          <Link className="flex-row-icon">
            {props.cardInfo.textButton} <img src={ArrowRightIcon} />
          </Link>
        </div>
      </div>
    </Link>
  );
}

export default CardMenu;
