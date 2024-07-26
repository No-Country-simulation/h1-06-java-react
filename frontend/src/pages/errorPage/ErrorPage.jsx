import './ErrorPage.css'
import { Link } from 'react-router-dom'

function ErrorPage() {
  return (
    <div className="error-page">
      <p>404</p>
      <Link to={'/'}>Volver al inicio</Link>
    </div>
  )
}

export default ErrorPage
