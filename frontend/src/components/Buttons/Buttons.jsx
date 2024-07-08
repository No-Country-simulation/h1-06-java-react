/* eslint-disable react/prop-types */
import "./Buttons.css";
const ButtonVariants = {
  primary: "primary-button",
  secondary: "secondary-button",
};

const Buttons = ({ variant = "primary", label, onClick, style }) => (
  <button className={ButtonVariants[variant]} onClick={onClick} style={style}>
    {label}
  </button>
);

export default Buttons;
