/* eslint-disable react/prop-types */
import { useState, useEffect } from 'react'
import searchImage from '/public/assets/icons/search.svg'
import { GetPatientBySurname } from '../../../../services/Doctor/GetPatientBySurname'
import { useUserLogin } from '../../../../store/UserLogin'

function Search({ setPatients }) {
  const [query, setQuery] = useState('')
  const { user } = useUserLogin()

  useEffect(() => {
    const fetchPatients = async () => {
      if (query.trim() === '') {
        setPatients([])
        return
      }
      const response = await GetPatientBySurname(user, query)
      setPatients(response)
    }

    const timeoutId = setTimeout(() => {
      fetchPatients()
    }, 300)

    return () => clearTimeout(timeoutId)
  }, [query, user, setPatients])

  const handleInputChange = (e) => {
    setQuery(e.target.value)
  }

  return (
    <section className="contentSearch">
      <input
        type="text"
        placeholder="Ingrese el apellido del paciente"
        className="inputSearch"
        value={query}
        onChange={handleInputChange}
      />
      <img
        src={searchImage}
        className="imgSearch"
        alt="Search"
        onClick={() => {}}
      />
    </section>
  )
}

export default Search
