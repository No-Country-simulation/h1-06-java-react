import ButtonContainer from "./components/ButtonContainer";
import LogoContainer from "./components/LogoContainer";
import TextContainer from "./components/TextContainer";

function Welcome() {
  return (
    <div id="welcome">
      <div className="modal">
        <LogoContainer />
        <TextContainer />
        <ButtonContainer />
      </div>
    </div>
  );
}

export default Welcome;
