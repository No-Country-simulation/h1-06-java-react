import { Link } from 'react-router-dom'
import chevronLeft from '/public/assets/icons/chevronLeft.svg'
import EditDoctorDataForm from '../editDoctorData/components/EditDoctorDataForm'
import './EditDoctorData.css'

function EditDoctorData() {
  return (
    <div id="profile">
      <div className="profile-header">
        <Link to={'/doctor/perfil'} className="profile-header-back">
          <img src={chevronLeft} />
        </Link>
        <div>
          <h2>Editar</h2>
        </div>
        <div></div>
      </div>
      <div id="edit-personal-data">
        <EditDoctorDataForm />
      </div>
    </div>
  )
}

export default EditDoctorData
