import React, { useEffect, useState } from 'react'
import { Checkbox } from 'antd';
import axios from 'axios';
import { getCars } from '../pages/FilterPage';


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

    props.setLoading(true)
    const updatedFilters = [...filters];
    if (e.target.checked) {
      updatedFilters.push(e.target.value);
    } else {
      const index = updatedFilters.indexOf(e.target.value);
      if (index !== -1) {
        updatedFilters.splice(index, 1);
      }
    }
  
    setFilters(updatedFilters);
  
    let x = props.filters;
    x[props.filtre] = updatedFilters;
  
    Object.keys(x).forEach((key) => {
      if (x[key].length === 0) {
        delete x[key];
      }
    });
  
    props.setFilters(x);
  
  
    axios
      .post(process.env.REACT_APP_API_URL + 'vehicles/filters', x, {
        headers: {
          "Content-Type": "application/json",
          Authorization: 'Bearer ' + localStorage.getItem('accessToken'),
        },
      })
      .then((res) => {
        props.setLoading(false)
        props.setData(getCars(res));
      })
      .catch((err) => {
        props.setLoading(false)
        console.log(err);
      });
  };
  

  return (
    <div className='ContainerFiltre'>
      {props.name}
      <br />
      <Checkbox.Group style={{ width: '100%' }} >
        {data.map((item) => <Checkbox onChange={HandleChange} key={item.id} value={item.id}>{item.name}</Checkbox>)}
      </Checkbox.Group>
    </div>
  )
}
