import React from 'react'
import Filter from './Filter'

export default function Filters(props) {
  return (
    <div className='ContainerFilters'>
        <Filter setLoading={props.setLoading} setData={props.setData} name={"Brands"}  url={"brands"} filters={props.filters} setFilters={props.setFilters} filtre={"brandIds"}/>
        <Filter setLoading={props.setLoading} setData={props.setData} name={"Models"}  url={"models"} filters={props.filters} setFilters={props.setFilters} filtre={"modelIds"}/>
        <Filter setLoading={props.setLoading} setData={props.setData} name={"Configurations"} url={"configurations"} filters={props.filters} setFilters={props.setFilters} filtre={"configurationIds"}/>
        <Filter setLoading={props.setLoading} setData={props.setData} name={"Colors"}  url={"colors"} filters={props.filters} setFilters={props.setFilters} filtre={"colorIds"}/>
    </div>
  )
}
