import Card from './components/Card'
import DataPatient from './components/DataPatient'
import DataTutor from './components/DataTutor'
import './HistoryPatient.css'
import microphone from '/public/assets/icons/microphone.svg'

function HistoryPatient() {
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
      <h3 className="titleHistoryPatient">
        Formato de Referencia de Pacientes
      </h3>
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
            <select name="genre" id="genre" className="inputDataPatient">
              <option className="interFont" value="">
                Patología
              </option>
              <option value="femenino">Femenino</option>
              <option value="masculino">Masculino</option>
              <option value="otro">Otro</option>
            </select>
          </div>
          <div className="contentLabelAndInput">
            <label htmlFor="patology" className="labelDataPatient">
              Medicamento
            </label>
            <select name="genre" id="genre" className="inputDataPatient">
              <option className="interFont" value="">
                Código
              </option>
              <option value="femenino">Femenino</option>
              <option value="masculino">Masculino</option>
              <option value="otro">Otro</option>
            </select>
          </div>
          <div className="contentLabelAndInput">
            <label htmlFor="patology" className="labelDataPatient">
              Dosís
            </label>
            <select name="genre" id="genre" className="inputDataPatient">
              <option className="interFont" value="">
                mg
              </option>
              <option value="femenino">Femenino</option>
              <option value="masculino">Masculino</option>
              <option value="otro">Otro</option>
            </select>
          </div>
          <div className="contentLabelAndInput">
            <label htmlFor="patology" className="labelDataPatient">
              Frecuencia
            </label>
            <select name="genre" id="genre" className="inputDataPatient">
              <option className="interFont" value="">
                Horas
              </option>
              <option value="femenino">Femenino</option>
              <option value="masculino">Masculino</option>
              <option value="otro">Otro</option>
            </select>
          </div>
          <button className="btnHistoryPatient">Confirmar</button>
        </form>
      </section>
    </div>
  )
}

export default HistoryPatient
