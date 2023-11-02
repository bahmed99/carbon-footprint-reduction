import React from 'react'
import Filter from './Filter'

export default function Filters(props) {
  return (
    <div className='ContainerFilters'>
        <Filter data={props.data} setLoading={props.setLoading} setData={props.setData} name={"Brands"}  url={"brands"} filters={props.filters} setFilters={props.setFilters} filtre={"brandIds"}/>
        <Filter data={props.data} setLoading={props.setLoading} setData={props.setData} name={"Models"}  url={"models"} filters={props.filters} setFilters={props.setFilters} filtre={"modelIds"}/>
        <Filter data={props.data} setLoading={props.setLoading} setData={props.setData} name={"Configurations"} url={"configurations"} filters={props.filters} setFilters={props.setFilters} filtre={"configurationIds"}/>
        <Filter data={props.data} setLoading={props.setLoading} setData={props.setData} name={"Colors"}  url={"colors"} filters={props.filters} setFilters={props.setFilters} filtre={"colorIds"}/>
    </div>
  )
}
