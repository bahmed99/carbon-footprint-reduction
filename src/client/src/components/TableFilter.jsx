import React,{useState} from 'react'
import { Table } from 'antd';

export default function TableFilter(props) {

    const [loading, setLoading] = useState(props.loading)
    
    const tableProps = {
        loading,
      };


  return (
    <div className='ContainerTable'>
        <Table  columns={props.columns} dataSource={props.data} {...tableProps} size='small' bordered/>
    </div>
  )
}
