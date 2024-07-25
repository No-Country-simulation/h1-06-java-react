/* eslint-disable react/prop-types */
import { useState } from 'react'
import searchImage from '/public/assets/icons/search.svg'
import axios from 'axios'

function Search({ setPatients }) {
  const [query, setQuery] = useState('')

  const handleSearch = async () => {
    if (query) {
      try {
        const response = await axios.get(
          `http://3.12.169.103:8080/api/v1/patient/surname/${query}/true`
        )
        setPatients(response.data)
      } catch (error) {
        console.error('Error fetching patients:', error)
        setPatients([])
      }
    }
  }

  const handleInputChange = (e) => {
    setQuery(e.target.value)
  }

  return (
    <section className="contentSearch">
      <input
        type="text"
        placeholder="DNI o apellido"
        className="inputSearch"
        value={query}
        onChange={handleInputChange}
      />
      <img
        src={searchImage}
        className="imgSearch"
        alt="Search"
        onClick={handleSearch}
      />
    </section>
  )
}

export default Search
