import React, { useEffect , useState} from 'react';
import { Tag , Table} from 'antd';


export default function FavoritesPage() {


    const [favorites, setFavorites] = useState([]);
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
        }
        ]);
    useEffect(() => {
        const favorites = JSON.parse(localStorage.getItem('favoriteCars')) || [];
        setFavorites(favorites);
    }, [favorites]);


    return (
        <div className='favoritesPage'>
            <h2 className='favoritesPage-title'>You can find here all your favorite cars.</h2>
            <Table dataSource={favorites} columns={columns} />            
        </div>
    )
}
