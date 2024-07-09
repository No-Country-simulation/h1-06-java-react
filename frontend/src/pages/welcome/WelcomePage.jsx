import "./WelcomePage.css";
import imageOne from "../../../public/assets/images/welcome-1.svg";
import imageTwo from "../../../public/assets/images/welcome-2.svg";
import imageThree from "../../../public/assets/images/welcome-3.svg";
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
function WelcomePage() {
  const navigateTo = useNavigate();
  const [images] = useState([
    { id: 1, image: imageOne, text: "Atención medica completa e integral" },
    { id: 2, image: imageTwo, text: "Un futuro sin listas de espera..." },
    { id: 3, image: imageThree, text: "Te acompanamos en cada paso" },
  ]);
  const [imageSelection, setImageSelection] = useState({});

  useEffect(() => {
    setImageSelection(images[0]);
  }, []);

  const handleClick = () => {
    const currentIndex = images.findIndex(image => image.id === imageSelection.id);
    const nextImageIndex = (currentIndex + 1) % images.length;
    setImageSelection(images[nextImageIndex]);
    if (nextImageIndex === 0) {
        navigateTo("/welcome");
      }
  };

  return (
    <div
      id="welcome-page"
      style={{
        backgroundImage: `url(${imageSelection?.image}`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        backgroundRepeat: "no-repeat",
      }}
      onClick={handleClick}
    >
      <div id="welcome-button">
        <Link to="/login"
          className={imageSelection?.id === 1 ? "classShow" : "classHidden"}
        >
          Omitir
        </Link>
      </div>
      <div className="welcome-text">
        <span className="just-another-hand-regular">{imageSelection?.text}</span>
        <div id="welcome-markers">
          {images.map((index) => (
            <div
              key={index.id}
              className="markers"
              style={{
                backgroundColor:
                  imageSelection?.id == index.id ? "#9ACABA" : "#E8CD54",
              }}
            ></div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default WelcomePage;

{
  /*                */
}
