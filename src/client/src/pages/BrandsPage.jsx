import React, { useState, useEffect } from 'react'
import LogoImg from "../assets/images/logo2.png";
import Navbar from '../components/Navbar';
import Button from 'react-bootstrap/Button';
import Collapse from 'react-bootstrap/Collapse';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Axios from 'axios';

const brands = [
    {
        name: "BMW",
        count: "10"
    },
    {
        name: "Mercedes",
        count: "12500"
    }
    ,
    {
        name: "Audi",
        count: "12500"
    }
    ,
    {
        name: "Volkswagen",
        count: "12500"
    }
    ,
    {
        name: "Seat",
        count: "12500"
    }

]
export default function BrandsPage() {
    const [showBrandInput, setShowBrandInput] = useState(false);
    const [newBrandName, setNewBrandName] = useState('');
    // const Authorization = 'Bearer' + localStorage.getItem('accessToken');
    // useEffect(() => {
    //     //get brands
    //     Axios.get(process.env.REACT_APP_API_URL + 'countByBrandName', {
    //         headers: {
    //             Authorization: Authorization
    //         }
    //     }).then((response) => {
    //         console.log(response);
    //     });
    // }, []);
    const newBrand = () => {
        setShowBrandInput(!showBrandInput);
    };

    return (
        <>
            <Navbar />

            <div className='brands-page-container'>
                <h2 className='brands-page-title'>You can find here all the brands we have in our database.</h2>
                <div className='brands-page-list'>
                    {brands.map((brand, index) => {
                        return (
                            <div className='brands-page-item'>
                                <div className='brands-page-item-name'>{brand.name}</div>
                                <div className='brands-page-item-count'>{brand.count}</div>
                            </div>
                        )
                    })}
                </div>
                <div className='adding-brand-container'>
                <h2 className='brands-page-subtitle'>You can add a new brand here</h2>
                <Button className='adding-brand-button' onClick={newBrand}>
                    {showBrandInput ? 'Cancel' : 'Add a brand'}
                </Button>
                <Collapse in={showBrandInput}>
                            <Form className='adding-brand-form'>
                            <Form.Group
                                className='mb-3 cars-form-input'
                                controlId='brandInput'
                            >
                               
                                <Form.Control
                                    type='text'
                                    placeholder='Enter the new brand'
                                    value={newBrandName}
                                    onChange={(e)=>{setNewBrandName(e.target.value)}}
                                    className='cars-form-input-field'
                                />
                            </Form.Group>
                            <Button className='adding-brand-button' onClick={() => { console.log(newBrandName) }}>
                                Add Brand
                            </Button>
                            </Form>
                                

            
                </Collapse>
                </div>
            </div>
        </>
    )
}
