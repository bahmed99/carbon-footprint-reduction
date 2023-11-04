import React,{useState} from 'react'
import { Table } from 'antd';
import BrandsTab from './BrandsTab';

export default function TableFilter(props) {

    const [loading, setLoading] = useState(props.loading)
    
    const tableProps = {
        loading,
      };


  return (
    <div className='ContainerTable'>
        <BrandsTab data={props.data} setLoading={props.setLoading} setData={props.setData} name={"Brands"}  url={"brands"} filters={props.filters} setFilters={props.setFilters} filtre={"brandIds"}/>
        <Table  columns={props.columns} dataSource={props.data} {...tableProps} size='small' bordered/>
    </div>
  )
}
