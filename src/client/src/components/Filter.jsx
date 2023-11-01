import React, { useEffect, useState } from 'react'
import { Checkbox } from 'antd';
import axios from 'axios';

export default function Filter(props) {

  const [data, setData] = useState([])
  const [filters,setFilters] = useState([])

  useEffect(() => {
    axios.get(process.env.REACT_APP_API_URL + props.url, {
      "headers": {
        "Authorization": 'Bearer ' + localStorage.getItem('accessToken'),
      }
    })
      .then((res) => setData(res.data))
      .catch((err) => console.log(err))
  }, [])

  const HandleChange = (e) => {

    if(e.target.checked){
      setFilters([...filters,e.target.value])
    }
    else
    {
      setFilters(filters.filter((item)=> item !== e.target.value))
    }

    let x = props.filters
    x[props.name] = filters

    props.setFilters(x)

    axios.get(process.env.REACT_APP_API_URL + 'vehicles/Filters', {
      "headers": {
        "Authorization": 'Bearer ' + localStorage.getItem('accessToken'),
      }
    }).then((res) => {
      console.log("test=  ",res.data)
    }
    ).catch((err) => {
      console.log(err)
    })
  }

  return (
    <div className='ContainerFiltre'>
      {props.name}
      <br />
      <Checkbox.Group style={{ width: '100%' }} onChange={(e) => console.log(e)}>
        {data.map((item) => <Checkbox onChange={(e)=>HandleChange(e)} key={item.id} value={item.id}>{item.name}</Checkbox>)}
      </Checkbox.Group>
    </div>
  )
}
