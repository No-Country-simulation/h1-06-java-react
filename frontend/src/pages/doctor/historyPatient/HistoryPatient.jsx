import { useEffect, useState } from 'react'
import Card from './components/Card'
import DataPatient from './components/DataPatient'
import DataTutor from './components/DataTutor'
import './HistoryPatient.css'
import microphone from '/public/assets/icons/microphone.svg'
import chevronLeft from '/public/assets/icons/chevronLeft.svg'
import axios from 'axios'
import { Link } from 'react-router-dom'

function HistoryPatient() {
  const [medicines, setMedicines] = useState([])
  const [pathologies, setPathologies] = useState([])

  useEffect(() => {
    const fetchMedicines = async () => {
      try {
        const response = await axios.get(
          'http://localhost:7082/api/v1/medicine'
        )
        setMedicines(response.data)
      } catch (error) {
        console.error('Error fetching medicines:', error)
      }
    }

    const fetchPathologies = async () => {
      try {
        const response = await axios.get(
          'http://localhost:7082/api/v1/pathology'
        )
        setPathologies(response.data)
      } catch (error) {
        console.error('Error fetching pathologies:', error)
      }
    }

    fetchMedicines()
    fetchPathologies()
  }, [])

  const obj = {
    1: {
      title: 'Talla',
      text: '120 cm',
      icon: '/public/assets/icons/talla.svg',
    },
    2: {
      title: 'Peso',
      text: '15 kg',
      icon: '/public/assets/icons/peso.svg',
    },
    3: {
      title: 'IMC',
      text: '19 inc',
      icon: '/public/assets/icons/imc.svg',
    },
    4: {
      title: 'FC',
      text: '80/120 lpm',
      icon: '/public/assets/icons/fc.svg',
    },
    5: {
      title: 'FR',
      text: '15 kg',
      icon: '/public/assets/icons/fr.svg',
    },
    6: {
      title: 'Presión art.',
      text: '120/80 mm Hg',
      icon: '/public/assets/icons/imc.svg',
    },
  }

  const objArray = Object.entries(obj)

  return (
    <div className="contentHistoryPatient">
      <div className="contentTitleHistoryPatient">
        <Link to="/doctor/turnos" className="linkHistoryPatient">
          <img
            src={chevronLeft}
            alt="ir hacia atras"
            className="BackHistoryPatient"
          />
        </Link>
        <h3 className="titleHistoryPatient">
          Formato de Referencia de Pacientes
        </h3>
      </div>
      <DataPatient />
      <DataTutor />
      <h4>Exámen Físico</h4>
      <section className="contentCardsHistoryPatient">
        {objArray.map(([key, value]) => (
          <Card
            key={key}
            title={value.title}
            text={value.text}
            icon={value.icon}
          />
        ))}
      </section>
      <hr />
      <section className="contentPlanHistoryPatient">
        <h4 className="subtitleHistoryPatient">Análisis y Plan de Atención</h4>
        <article className="contentTextHistoryPatient">
          <img src={microphone} className="microHistoryPatient" />
          <p className="textHistoryPatient">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Aut iste
            consectetur veniam quae expedita minus fugit laborum, dicta est
            illum unde accusamus, quia dolore totam velit mollitia nam
            reprehenderit aperiam!
          </p>
        </article>
        <form className="contentFormHistoryPatient">
          <div className="contentLabelAndInput">
            <label htmlFor="patology" className="labelDataPatient">
              Diagnósgtico
            </label>
            <select name="genre" id="patology" className="inputDataPatient">
              <option className="interFont" value="">
                seleccione el diagnóstico
              </option>
              {pathologies.map((pathology) => (
                <option key={pathology.id} value={pathology.name}>
                  {pathology.name}
                </option>
              ))}
            </select>
          </div>
          <div className="contentLabelAndInput">
            <label htmlFor="medicament" className="labelDataPatient">
              seleccione el medicamento
            </label>
            <select name="genre" id="medicament" className="inputDataPatient">
              <option className="interFont" value="">
                medicamento
              </option>
              {medicines.map((medicine) => (
                <option key={medicine.id} value={medicine.name}>
                  {medicine.name}
                </option>
              ))}
            </select>
          </div>
          <div className="contentLabelAndInput">
            <label htmlFor="dosis" className="labelDataPatient">
              Dosís
            </label>
            <select name="genre" id="dosis" className="inputDataPatient">
              <option className="interFont" value="">
                seleccione la disís
              </option>
              <option value="5mg">5 mg</option>
              <option value="10mg">10 mg</option>
              <option value="20mg">20 mg</option>
              <option value="50mg">50 mg</option>
              <option value="100mg">100 mg</option>
            </select>
          </div>
          <div className="contentLabelAndInput">
            <label htmlFor="frecuency" className="labelDataPatient">
              Frecuencia
            </label>
            <select name="genre" id="frecuency" className="inputDataPatient">
              <option className="interFont" value="">
                seleccione la frecuencia
              </option>
              <option value="every4hours">Cada 4 horas</option>
              <option value="every6hours">Cada 6 horas</option>
              <option value="every8hours">Cada 8 horas</option>
              <option value="every12hours">Cada 12 horas</option>
              <option value="onceaday">Una vez al día</option>
            </select>
          </div>
          <button className="btnHistoryPatient">Confirmar</button>
        </form>
      </section>
    </div>
  )
}

export default HistoryPatient
