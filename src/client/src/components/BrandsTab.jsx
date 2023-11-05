import React, { useEffect, useState } from 'react';
import { Tabs } from 'antd';
import { getCars } from '../pages/FilterPage';
import axios from "axios";
// import styled from 'styled-components';

// const Tab = styled.button`
//   font-size: 20px;
//   padding: 10px 60px;
//   cursor: pointer;
//   opacity: 0.6;
//   background: white;
//   border: 0;
//   outline: 0;
//   ${({ active }) =>
//     active==="true" &&
//     `
//     border-bottom: 2px solid black;
//     opacity: 1;
//   `}
// `;
// const ButtonGroup = styled.div`
//   display: flex;
// `;


export default function BrandsTab(props) {
    const Authorization = 'Bearer' + localStorage.getItem('accessToken');

    const [data, setData] = useState([]);
    const [brands, setBrands] = useState([]);
    const [active, setActive] = useState(-1);
    useEffect(() => {
        axios.get(process.env.REACT_APP_API_URL + props.url, {
            headers: {
                Authorization: Authorization,
            }
        }).then((res) => {
            let x = props.filters;

            Object.keys(x).forEach((key) => {
                if (x[key].length === 0) {
                    delete x[key];
                }
            });

            delete x[props.filtre];

            axios.post(process.env.REACT_APP_API_URL + `vehicles/filters/CountBrand`, x, {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: Authorization,
                },
            }).then((resp) => {
                const countByBrand = resp.data;

                const items = res.data.map((item, index) => {
                    const carCount = countByBrand.find((element) => element[0] === item.id);
                    return {
                        key: item.id,
                        label: item.name + " (" + (carCount ? carCount[1] : 0) + ")",
                    };
                });

                const sum = countByBrand.reduce((a, b) => a + b[1], 0);
                items.unshift({
                    key: -1,
                    label: "All" + " (" + sum + ")",
                });

                setData(items);

            }).catch((err) => console.log(err));

        }).catch((err) => console.log(err));

        
    }, [props.data, props.filters]);

    const HandleChange = (e) => {
        props.setLoading(true);
        const x = { ...props.filters }; // Cloner l'objet filters pour éviter les modifications directes

        if (e === -1) {
            const updatedBrands = [];
            setBrands(updatedBrands); // Mettre à jour l'état local

            if (Object.values(x).every(liste => liste.length === 0)) {
                axios.get(process.env.REACT_APP_API_URL + `vehicles/page/${props.pageNumber - 1}/${props.pageSize}`, {
                    headers: {
                        Authorization: Authorization,
                    }
                }).then((res) => {
                    props.setData(getCars(res));
                }).catch((err) => {
                    console.log(err);
                });
            }
        } else {
            const updatedBrands = [e];
            setBrands(updatedBrands); // Mettre à jour l'état local
            x[props.filtre] = updatedBrands;
        }

        // Supprimer les clés du filtre si les listes sont vides
        Object.keys(x).forEach((key) => {
            if (x[key].length === 0) {
                delete x[key];
            }
        });

        props.setFilters(x);

        axios.post(process.env.REACT_APP_API_URL + `vehicles/filters/page/${props.pageNumber - 1}/${props.pageSize}`, x, {
            headers: {
                "Content-Type": "application/json",
                Authorization: Authorization,
            }
        }).then((res) => {
            props.setLoading(false);
            props.setData(getCars(res));
        }).catch((err) => {
            props.setLoading(false);
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
            {/* <ButtonGroup>
            {
                data.map((item, index) => (
                    <Tab
                        key={index}
                        active={item.key === active ? 'true' : 'false'}
                        onClick={() => {
                            setActive(item.key);
                            HandleChange(item.key);
                        }}
                    >
                        {item.label}
                    </Tab>
                ))
                        }

            </ButtonGroup> */}

            


         </div>
    )
}
