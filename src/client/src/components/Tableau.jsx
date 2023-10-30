import React,{useState} from 'react'
import { Table } from 'antd';

export default function Tableau(props) {

    const [loading, setLoading] = useState(false)
    

    const tableProps = {
        loading,
      };


  return (
    <div className='ContainerTable'>
        <Table columns={props.columns} dataSource={props.data} {...tableProps} size='small' bordered/>
    </div>
  )
}
