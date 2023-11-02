import React, { useState, useEffect } from 'react'
import Filters from '../components/Filters'
import TableFilter from '../components/TableFilter'
import { Tag } from 'antd';
import axios from 'axios'
import Navbar from '../components/Navbar';


export default function FilterPage() {


  const [data, setData] = useState([])
  const [loading, setLoading] = useState(false)
  const [filters, setFilters] = useState({
    brandIds: [],
    modelIds: [],
    configurationIds: [],
    colorIds: []
  })


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
            color= conf.length > 30 ? 'volcano' : color;
            color= conf.length > 75 ? 'red' : color;
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

  useEffect(() => {
    setLoading(true)

    axios.get(process.env.REACT_APP_API_URL + 'vehicles', {
      "headers": {
        "Authorization": 'Bearer ' + localStorage.getItem('accessToken'),
      }
    }).then((res) => {

      setData(getCars(res))
    }
    ).catch((err) => {
      console.log(err)
    })

  }, [filters])

  return (
    <div>
      <Navbar />
      <div className='ContainerFiltrePage'>
        <Filters data={data} filters={filters} setFilters={setFilters} setData={setData} setLoading={setLoading} />
        <TableFilter data={data} columns={columns} loading={loading} />
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
