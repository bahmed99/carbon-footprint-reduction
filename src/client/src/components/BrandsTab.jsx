import React, { useEffect, useState } from 'react'
import { Tabs } from 'antd';
import { getCars } from '../pages/FilterPage';
import { NumberOfCarsByFiltre } from './Filter'
import axios from "axios";

export default function BrandsTab(props) {
    const Authorization = 'Bearer' + localStorage.getItem('accessToken');

    const [data, setData] = useState([])
    const [brands, setBrands] = useState([])

    useEffect(() => {
        axios.get(process.env.REACT_APP_API_URL + props.url, {
            headers: {
                Authorization: Authorization,
            }
        })
        .then(async (res) => {
            const items = await Promise.all(res.data.map(async (item, index) => {
                const carCount = await NumberOfCarsByFiltreAndModel(props.filters, props.filtre, props.url.slice(0, -1), item);
                return {
                    key: item.id,
                    label: item.name + " (" + carCount + ")"
                };
            }));

            const allItemCount = await NumberOfCarsByFiltreAndModel(props.filters, props.filtre, props.url.slice(0, -1), "", true);
    
            //push au debut
            items.unshift({
                key: -1,
                label: "All" + " (" + allItemCount + ")",
            });
    
            setData(items);
        })
        .catch((err) => console.log(err))
    }, [props.data]);
    

    const HandleChange = (e) => {

        props.setLoading(true)

        let x = props.filters;

        if (e == -1) {

            const updatedBrands = [];

            setBrands(updatedBrands);

            x[props.filtre] = updatedBrands;


            if (Object.values(data).every(liste => liste.length === 0)) {
                axios.get(process.env.REACT_APP_API_URL + 'vehicles', {
                    headers: {
                        Authorization: Authorization,
                    }
                }).then((res) => {

                    props.setData(getCars(res))
                })
                    .catch((err) => console.log(err))

            }


        }

        else {


            const updatedBrands = [...brands];
            updatedBrands[0] = e;


            setBrands(updatedBrands);

            x[props.filtre] = updatedBrands;



        }
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
        <div className='ContainerBrandsTab'>

            <Tabs
                defaultActiveKey="-1"
                items={data}
                onChange={HandleChange}
                indicatorSize={(origin) => origin - 16}
            />
        </div>
    )
}

async function NumberOfCarsByFiltreAndModel(filters, filtre, brand, item="",all=false) {
    const Authorization = 'Bearer' + localStorage.getItem('accessToken');
    let count = 0;

    Object.keys(filters).forEach((key) => {
        if (filters[key].length === 0) {
            delete filters[key];
        }
    });

    delete filters[filtre];

    try {
        const res = await axios.post(process.env.REACT_APP_API_URL + 'vehicles/filters', filters, {
            headers: {
                "Content-Type": "application/json",
                Authorization: Authorization,
            },
        });

        const cars = getCars(res);

        if(all) return cars.length;

        cars.forEach((car) => {
            if (car[brand].includes(item.name)) {
                count++;
            }
        });

        

        return count;
    } catch (err) {
        console.error(err);
        return 0; 
    }
}
