import { Link } from "react-router-dom";
import chevronLeft from "../../../../../public/assets/icons/chevronLeft.svg";
import EditDataForm from "./components/EditDataForm";
import "./EditPersonalData.css";

function EditPersonalData() {
  return (
    <div id="profile">
      <div className="profile-header">
        <Link to={"/patient/profile"} className="profile-header-back">
          <img src={chevronLeft} />
        </Link>
        <div>
          <h2>Editar</h2>
        </div>
        <div></div>
      </div>
      <div id="edit-personal-data">
        <EditDataForm />
      </div>
    </div>
  );
}

export default EditPersonalData;
