import React from 'react'
import Filter from './Filter'

export default function Filters(props) {
  return (
    <div className='ContainerFilters'>
        <Filter name={"Brands"}  url={"brands"} filters={props.filters} setFilters={props.setFilters} filtre={"brandIds"}/>
        <Filter name={"Models"}  url={"models"} filters={props.filters} setFilters={props.setFilters} filtre={"modelIds"}/>
        <Filter name={"Configurations"} url={"configurations"} filters={props.filters} setFilters={props.setFilters} filtre={"configurationIds"}/>
        <Filter name={"Colors"}  url={"colors"} filters={props.filters} setFilters={props.setFilters} filtre={"colorIds"}/>
    </div>
  )
}
