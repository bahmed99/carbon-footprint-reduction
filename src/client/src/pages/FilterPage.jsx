import React, { useState, useEffect } from 'react'
import Filtres from '../components/Filters'
import TableFilter from '../components/TableFilter'
import { Space, Tag } from 'antd';
import axios from 'axios'


export default function FilterPage() {


  const [data, setData] = useState([])
  const [loading, setLoading] = useState(false)
  const [filters, setFilters] = useState({
    Brands: [],
    Models: [],
    Configurations: [],
    Colors: []
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
      key: 'configurations',
      dataIndex: 'configurations',
      // render: (_, { tags }) => (
      //   <>
      //     {tags.map((tag) => {
      //       let color = tag.length > 5 ? 'geekblue' : 'green';
      //       if (tag === 'loser') {
      //         color = 'volcano';
      //       }
      //       return (
      //         <Tag color={color} key={tag}>
      //           {tag.toUpperCase()}
      //         </Tag>
      //       );
      //     })}
      //   </>
      // ),
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

  }
    , [])

  return (
    <div className='ContainerFiltrePage'>
      <Filtres filters={filters} setFilters={setFilters} />
      <TableFilter data={data} columns={columns} loading={loading} />
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
