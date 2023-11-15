import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';
import Button from 'react-bootstrap/Button';
import Collapse from 'react-bootstrap/Collapse';
import Form from 'react-bootstrap/Form';
import Swal from 'sweetalert2'
import Axios from 'axios';
import Alert from 'react-bootstrap/Alert';
import ReactLoading from "react-loading";


// const brands = [
//     {
//         name: "BMW",
//         count: "10"
//     },
//     {
//         name: "Mercedes",
//         count: "12500"
//     }
//     ,
//     {
//         name: "Audi",
//         count: "12500"
//     }
//     ,
//     {
//         name: "Volkswagen",
//         count: "12500"
//     }
//     ,
//     {
//         name: "Seat",
//         count: "12500"
//     }

// ]
export default function BrandsPage() {
    const [showBrandInput, setShowBrandInput] = useState(false);
    const [newBrandName, setNewBrandName] = useState('');
    const [brandAddingError, setBrandAddingError] = useState('');
    const [brands,setBrands] = useState([]);
    const [loading, setLoading] = useState(false);

    const navigate = useNavigate();

    const Authorization = 'Bearer' + localStorage.getItem('accessToken');
    useEffect(() => {
        //get brands
        Axios.get(process.env.REACT_APP_API_URL + 'vehicles/countByBrandName', {
            headers: {
                Authorization: Authorization
            }
        }).then((response) => {
            console.log(response);
            console.log(response.data);
            setBrands(response.data);
        });
    }, []);
    const newBrand = () => {
        if (showBrandInput) {
            setBrandAddingError('');
        }
        setShowBrandInput(!showBrandInput);
    };
    const submitAddBrand = (e) => {
        e.preventDefault();
        setLoading(true);
        if (newBrandName === '') {
            setBrandAddingError('Please enter a brand name');
            return;
        }
        setBrandAddingError('');
        const json = {
            "name": newBrandName
        };  
        Axios.post(process.env.REACT_APP_API_URL + 'brands', json, {
            headers: {
                Authorization: Authorization
            }
        }).then((response) => {
           setLoading(false);
           Swal.fire({
                title: 'Success',
                text: 'Brand added successfully , you need to add models for this brand ',
                icon: 'success',
                confirmButtonText: 'Go to models page',
                cancelButtonText: 'Cancel',
                showCancelButton: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        navigate('/models');
                    }
              });
        }).catch((error) => {
            setLoading(false);
            console.log(error);
            Swal.fire({
                title: 'Error',
                text: 'An error occured while adding the brand',
                icon: 'error',
                confirmButtonText: 'OK'
              });
        });
    }


    return (
        <>
            <Navbar />

            <div className='brands-page-container'>
                <h2 className='brands-page-title'>You can find here all the brands we have in our database.</h2>
                <div className='brands-page-list'>
                    {brands.map((brand, index) => {
                        return (
                            <div className='brands-page-item'>
                                <div className='brands-page-item-name'>{brand[0]}</div>
                                <div className='brands-page-item-count'>{brand[1]}</div>
                            </div>
                        )
                    })}
                </div>
                <div className='adding-brand-container'>
                <h2 className='brands-page-subtitle'>You can add a new brand here</h2>
                <Button className='adding-brand-button' onClick={newBrand}>
                    {showBrandInput ? 'Cancel' : 'Add a brand'}
                </Button>
                {brandAddingError && (
                <Alert variant='danger' className='brand-form-error-alert'>
                    {brandAddingError}
                </Alert>
            )}  
                <Collapse in={showBrandInput}>
                            <Form className='adding-brand-form' onSubmit={submitAddBrand}>
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
                            <Button className='adding-brand-button' type='submit'>
                                Add Brand
                            </Button>
                            </Form>
                                

            
                </Collapse>
                <ReactLoading type={"spin"} color={"#EF7C00"} height={50} width={50}  hidden={!loading} />
                </div>
            </div>
        </>
    )
}
