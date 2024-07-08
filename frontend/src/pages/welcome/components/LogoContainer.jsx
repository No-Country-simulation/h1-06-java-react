import justinaLogo from "../../../../public/assets/images/justinaLogo.svg";

function LogoContainer() {
  return (
    <div id="logo-container">
      <div id="logo">
        <img src={justinaLogo} alt="Justina Logo" />
      </div>
    </div>
  );
}

export default LogoContainer;
