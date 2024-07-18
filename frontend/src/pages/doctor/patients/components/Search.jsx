import searchImage from '/public/assets/icons/search.svg'

function Search() {
  return (
    <section className='contentSearch'>
      <input type="number" placeholder="DNI" className='inputSearch' />
      <img src={searchImage} className='imgSearch'/>
    </section>
  )
}

export default Search
