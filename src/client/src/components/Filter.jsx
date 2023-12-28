import React, { useEffect, useState } from 'react'
import { Checkbox } from 'antd';
import axios from 'axios';
import { getCars } from '../pages/FilterPage';
import 'font-awesome/css/font-awesome.min.css';


export default function Filter(props) {
  const Authorization = 'Bearer' + localStorage.getItem('accessToken');

  const [data, setData] = useState([])
  const [filters, setFilters] = useState([])

  /* ajouter le collaps pour les filtres */
  const [isFilterOpen, setIsFilterOpen] = useState(false);
  const [arrowDirection, setArrowDirection] = useState("down");


  useEffect(() => {
    const handleData = (data) => {
      let x = props.filters;
      Object.keys(x).forEach((key) => {
        if (x[key].length === 0) {
          delete x[key];
        }
      });
  
      axios.post(process.env.REACT_APP_API_URL + `vehicles/filters/${props.url.slice(0, -1)}`, x, {
        headers: {
          "Content-Type": "application/json",
          Authorization: Authorization,
        },
      })
      .then((resp) => {
        // add count to data
        data.forEach((item, index) => {
          const carCount = resp.data.find((element) => element[0] === item.id);
          data[index].count = carCount ? carCount[1] : 0;
        });
        setData(data);
      })
      .catch((err) => console.log(err));
    };
  
    if (props.url === "colors") {
      let y = localStorage.getItem('colors');
      y = JSON.parse(y);
      handleData(y);
    } else {
      axios.get(process.env.REACT_APP_API_URL + props.url, {
        headers: {
          Authorization: Authorization,
        },
      })
      .then((res) => {
        handleData(res.data);
      })
      .catch((err) => console.log(err));
    }
  }, [props.filters, props.data]);
  
  

  const HandleChange = (e) => {

    props.setPageNumber(1)
    props.setPageSize(10)

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

    console.log(x)


    axios
      .post(process.env.REACT_APP_API_URL + `vehicles/filters/page/${props.pageNumber - 1}/${props.pageSize}`, x, {
        headers: {
          "Content-Type": "application/json",
          Authorization: Authorization,
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
      <div
        style={{ display: 'flex', alignItems: 'center', cursor: 'pointer' }}
        onClick={() => {
          setIsFilterOpen(!isFilterOpen);
          setArrowDirection(isFilterOpen ? 'down' : 'up');
        }}
      >
        {props.name}
        <div style={{ marginLeft: 'auto' }}>
          <i className={`fa fa-chevron-${arrowDirection}`} />
        </div>
      </div>
      {isFilterOpen && (
        <Checkbox.Group style={{ width: '100%' }}>
          {data.map((item, index) => (
            <Checkbox onChange={HandleChange} key={item.id} value={item.id}>
              {item.name} ({item.count})
            </Checkbox>
          ))}
        </Checkbox.Group>
      )}
    </div>
  );
}

