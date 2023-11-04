import React from 'react'
import Filter from './Filter'

export default function Filters(props) {
  return (
    <div className='ContainerFilters'>
        <Filter pageSize={props.pageSize} pageNumber={props.pageNumber} data={props.data} setLoading={props.setLoading} setData={props.setData} name={"Models"}  url={"models"} filters={props.filters} setFilters={props.setFilters} filtre={"modelIds"}/>
        <Filter pageSize={props.pageSize} pageNumber={props.pageNumber} data={props.data} setLoading={props.setLoading} setData={props.setData} name={"Configurations"} url={"configurations"} filters={props.filters} setFilters={props.setFilters} filtre={"configurationIds"}/>
        <Filter pageSize={props.pageSize} pageNumber={props.pageNumber} data={props.data} setLoading={props.setLoading} setData={props.setData} name={"Colors"}  url={"colors"} filters={props.filters} setFilters={props.setFilters} filtre={"colorIds"}/>
    </div>
  )
}
