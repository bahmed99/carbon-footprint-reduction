import React, { useState, useEffect } from 'react'
import Filters from '../components/Filters'
import TableFilter from '../components/TableFilter'
import { Tag } from 'antd';
import axios from 'axios'
import Stomp from 'stompjs';

export default function FilterPage() {

  const Authorization = 'Bearer' + localStorage.getItem('accessToken');
  const [data, setData] = useState([])
  const [pageNumber, setPageNumber] = useState(1)
  const [pageSize, setPageSize] = useState(10)
  const [loading, setLoading] = useState(false)
  const [countData, setCountData] = useState(0)
  const [filters, setFilters] = useState({})

  const socket = new WebSocket('ws://localhost:8080/chat', "v10.stomp");

  useEffect(() => {
    const stompClient = Stomp.over(socket);
    const onConnect = (frame) => {
      console.log('Connected: ' + frame);
      stompClient.subscribe('/topic/vehicles', function (message) {
        fetchData();
      });
    };

    stompClient.connect({}, onConnect);
  },[socket])

  const [columns, setColumns] = useState([
    {
      title: 'Model',
      dataIndex: 'model',
      key: 'model',
    },
    {
      title: 'Brand',
      dataIndex: 'brand',
      key: 'brand',
    },
    {
      title: 'Color',
      dataIndex: 'color',
      key: 'color',
      render: (_, { color }) => (
        <>
          <Tag color={color} style={{ color: 'black' }}>
            {color}
          </Tag>
        </>
      ),
    },
    {
      title: 'Year of creation',
      dataIndex: 'yearcreation',
      key: 'yearcreation',
    },
    {
      title: 'Configurations',
      dataIndex: 'configurations',
      key: 'configurations',
      render: (configurations) => (
        <span>
          {configurations[0].map((conf) => {
            let color = conf.length > 25 ? 'geekblue' : 'green';
            color = conf.length > 30 ? 'volcano' : color;
            color = conf.length > 75 ? 'red' : color;
            return (
              <Tag color={color} key={conf}>
                {conf}
              </Tag>
            );
          })}
        </span>
      ),
    },
    {
      title: 'Price',
      dataIndex: 'price',
      key: 'price',
    },
    // {
    //   title: 'Action',
    //   key: 'action',
    //   render: (_, record) => (
    //     <Space size="middle">
    //       <a>Invite {record.name}</a>
    //       <a>Delete</a>
    //     </Space>
    //   ),
    // },
  ])

  const fetchData = () => {
    setLoading(true);

    let x = filters;

    Object.keys(x).forEach((key) => {
      if (x[key].length === 0) {
        delete x[key];
      }
    }
    );  

    axios.post(process.env.REACT_APP_API_URL + `vehicles/filters/page/${pageNumber -1}/${pageSize}`, x,{
      headers: {
        Authorization: Authorization,
      }
    })
      .then((res) => {
        setData(getCars(res));
        setLoading(false);
              })
      .catch((err) => {
        console.log(err);
        setLoading(false);
      });

      console.log(x)
      axios.post(process.env.REACT_APP_API_URL + `vehicles/countByFilter`, x,{
        headers: {
          Authorization: Authorization,

        }
      })
        .then((res) => {
          setCountData(res.data)
        })
        .catch((err) => console.log(err))
  };

  useEffect(() => {
    fetchData();

  }, [pageNumber, pageSize,filters]);

  return (
    <div>
      <div className='ContainerFiltrePage'>
        <Filters  setPageNumber={setPageNumber} setPageSize={setPageSize} countData={countData} pageNumber={pageNumber} pageSize={pageSize} data={data} filters={filters} setFilters={setFilters} setData={setData} setLoading={setLoading} />
        <TableFilter countData={countData} pageNumber={pageNumber} pageSize={pageSize} setPageNumber={setPageNumber} setPageSize={setPageSize} filters={filters} setFilters={setFilters} data={data} columns={columns} loading={loading} setData={setData} setLoading={setLoading} />
      </div>
    </div>
  )
}

export function getCars(response) {
  let car = {
    key: '',
    model: '',
    brand: '',
    yearcreation: '',
    configurations: [],
    price: ''
  }
  let cars = []

  response.data.map((item) => {
    car.key = item.id
    car.model = item.model.name
    car.brand = item.model.brand.name
    car.yearcreation = item.yearOfCreation
    car.configurations = [item.configurations.map((item) => item.name)]
    car.price = item.priceWithoutConfiguration
    car.color = item.color.name
    cars.push(car)
    car = {
      key: '',
      model: '',
      brand: '',
      yearcreation: '',
      configurations: [],
      price: ''
    }
  })
  return cars
}
