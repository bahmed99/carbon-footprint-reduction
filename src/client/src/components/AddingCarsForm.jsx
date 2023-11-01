import React, { useState, useRef, useEffect } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Collapse from 'react-bootstrap/Collapse';
import Alert from 'react-bootstrap/Alert';
import Axios from 'axios';
import Swal from 'sweetalert2'




// const brands = [
//     {
//         id: 1,
//         name: "Brand A",
//         createdAt: "2023-11-01",
//         updatedAt: "2023-11-01"
//     },
//     {
//         id: 2,
//         name: "Brand B",
//         createdAt: "2023-11-01",
//         updatedAt: "2023-11-01"
//     },
//     {
//         id: 3,
//         name: "Brand C",
//         createdAt: "2023-11-01",
//         updatedAt: "2023-11-01"
//     }
// ];

// const models = [
//     {
//         id: 1,
//         name: "Model X",
//         createdAt: "2023-11-01",
//         updatedAt: "2023-11-01",
//         brandId: 1 // Corresponds to Brand A
//     },
//     {
//         id: 2,
//         name: "Model Y",
//         createdAt: "2023-11-01",
//         updatedAt: "2023-11-01",
//         brandId: 1 // Corresponds to Brand A
//     },
//     {
//         id: 3,
//         name: "Model Z",
//         createdAt: "2023-11-01",
//         updatedAt: "2023-11-01",
//         brandId: 2 // Corresponds to Brand B
//     },
//     {
//         id: 4,
//         name: "Model P",
//         createdAt: "2023-11-01",
//         updatedAt: "2023-11-01",
//         brandId: 3 // Corresponds to Brand C
//     }
// ];

// const colors = [
//     { id: 1, name: 'red' },
//     { id: 2, name: 'blue' },
//     { id: 3, name: 'black' },
//     { id: 4, name: 'white' },
//     { id: 5, name: 'grey' },
// ];

// const options = [
//     { id: 1, name: 'option1' },
//     { id: 2, name: 'option2' },
//     { id: 3, name: 'option3' },
//     { id: 4, name: 'option4' },
//     { id: 5, name: 'option5' },
// ];

const AddingCarsForm = () => {
    //data from backend
    const [brands,setBrands] = useState([])
    const [allModels, setAllModels] = useState([])
    const [colors, setColors] = useState([]);
    const [options, setOptions] = useState([]);


    //
    const [brand, setBrand] = useState('');
    const [newBrandName, setNewBrandName] = useState('');
    const [model, setModel] = useState('');
    const [newModelName, setNewModelName] = useState('');
    const [price , setPrice] = useState('')
    const [filteredModels, setFilteredModels] = useState([]);
    const [year, setYear] = useState('');
    const [color, setColor] = useState('');
    const [showBrandInput, setShowBrandInput] = useState(false);
    const [showModelInput, setShowModelInput] = useState(false);
    const [selectedOptions, setSelectedOptions] = useState([]);
    const [error, setError] = useState('');
    const errorRef = useRef(null);
    const Authorization = 'Bearer' + localStorage.getItem('accessToken');
      useEffect(() => {
        //get brands
        Axios.get(process.env.REACT_APP_API_URL + 'brands', {
            headers: {
                Authorization: Authorization
            }
        }).then((response) => {
            console.log(response);
            setBrands(response.data);
        });
        //gets all models
        Axios.get(process.env.REACT_APP_API_URL + 'models', {
            headers: {
                Authorization: Authorization
            }
        }).then((response) => {
            console.log("models",response);
            setAllModels(response.data);
        });
        //get colors
        Axios.get(process.env.REACT_APP_API_URL + 'colors', {
            headers: {
                Authorization: Authorization
            }
        }).then((response) => {
            console.log("colors",response);
            setColors(response.data);
        });
        //get options
        Axios.get(process.env.REACT_APP_API_URL + 'configurations', {
            headers: {
                Authorization: Authorization
            }
        }).then((response) => {
            console.log("configurations",response);
            setOptions(response.data);
        });
    }, []);

    const newBrand = () => {
        setBrand('');
        setShowBrandInput(!showBrandInput);
        setShowModelInput(true);
    };

    const newModel = () => {
        setModel('');
        setShowModelInput(!showModelInput);
    };
 

    //handlers
    const handleBrandChange = (e) => {
        const selectedBrandId = parseInt(e.target.value);
        console.log('selectedBrandId: ', selectedBrandId);
        setBrand(selectedBrandId);
        const modelsForSelectedBrand = allModels.filter((model) => model.brandId === selectedBrandId);
        setFilteredModels(modelsForSelectedBrand);
        console.log('modelsForSelectedBrand: ', modelsForSelectedBrand);
    };

    const handleModelChange = (e) => {
        setModel(e.target.value);
    };

    const handleYearChange = (e) => {
        setYear(parseInt(e.target.value));
    };

    const handleColorChange = (e) => {
        setColor(parseInt(e.target.value));
    };
    const handlePriceChange = (e) => {
        setPrice(parseFloat(e.target.value));
    };
    const handleCheckboxChange = (optionId) => {
        if (selectedOptions.includes(optionId)) {
            setSelectedOptions((prevOptions) =>
                prevOptions.filter((id) => id !== optionId)
            );
        } else {
            setSelectedOptions((prevOptions) => [...prevOptions, optionId]);
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Field validation
        if (!brand || !model || !year || !color || !price) {
            setError('Please fill out all fields.');
            errorRef.current?.scrollIntoView({ behavior: 'smooth' });
            return;
        }
        setError('');
        console.log('Brand: ', brand);
        console.log('Model: ', model);
        console.log('Year: ', year);
        console.log('Color: ', color);
        console.log('Selected options: ', selectedOptions);
        const configurations = selectedOptions.map(id => {
            return { id };
          });
        const json = {
            "yearOfCreation": year,
            "priceWithoutConfiguration": price,
            "model": {
              "id": model
            },
            "color": {
              "id": color
            },
            "configurations": configurations
        }
        console.log("json",json)        
        //post request to backend 
        //axios with headers bearer token
        Axios.post(process.env.REACT_APP_API_URL + 'vehicles'  , {
            "yearOfCreation": year,
            "priceWithoutConfiguration": price,
            "model": {
              "id": model
            },
            "color": {
              "id": color
            },
            "configurations": configurations
        }, {
            headers: {
                Authorization: Authorization
            }
        }).then((response) => {
            console.log(response);
            Swal.fire({
                title: 'Success!',
                text: 'Your car has been added to the database.',
                icon: 'success',
                confirmButtonText: 'Cool'
            })
        }).catch((error) => {
            console.log(error);
            Swal.fire({
                title: 'Error!',
                text: 'Your car has not been added to the database.',
                icon: 'error',
                confirmButtonText: 'Ok'
            })
        })
    };

  
    return (
        <Form className='cars-form-container' onSubmit={handleSubmit}>
            <div ref={errorRef} />
            {error && (
                <Alert variant='danger' className='cars-form-error-alert'>
                    {error}
                </Alert>
            )}
            <Container>
                <Row className='cars-form-row'>
                    <Col className='cars-form-col' xs={9}>
                        <Form.Select
                            className='cars-form-select'
                            onChange={handleBrandChange}
                            value={brand}
                        >
                            <option value=''>
                                {showBrandInput
                                    ? 'Please enter the new brand of the car'
                                    : 'Please choose the brand of the car'}
                            </option>
                            {!showBrandInput &&
                                brands.map((brand) => (
                                    <option key={brand.id} value={brand.id}>
                                        {brand.name}
                                    </option>
                                ))}
                        </Form.Select>
                    </Col>
                    {/* <Col className='cars-form-col'>
                        <Button className='cars-form-button' onClick={newBrand}>
                            {showBrandInput ? 'Cancel' : 'Add a brand'}
                        </Button>
                    </Col> */}
                </Row>
                <Collapse in={showBrandInput}>
                    <Row className='cars-form-row'>
                        <Col className='cars-form-col' xs={9}>
                            <Form.Group
                                className='mb-3 cars-form-input'
                                controlId='brandInput'
                            >
                                <Form.Label>New Brand:</Form.Label>
                                <Form.Control
                                    type='text'
                                    placeholder='Enter the new brand'
                                    value={newBrandName}
                                    onChange={(e)=>{setNewBrandName(e.target.value)}}
                                    className='cars-form-input-field'
                                />
                            </Form.Group>
                            <Button className='cars-form-button' onClick={()=>{console.log(newBrandName)}}>
                                Add Brand
                            </Button>
                        </Col>
                      
                    </Row>
                </Collapse>
                <Row className='cars-form-row'>
                    <Col className='cars-form-col' xs={9}>
                        <Form.Select className='cars-form-select' onChange={handleModelChange} value={model}>
                            <option value=''>
                                {showModelInput
                                    ? 'Please enter the new model of the car'
                                    : 'Please choose the model of the car'}
                            </option>
                            {!showModelInput &&
                                filteredModels.map((model) => (
                                    <option key={model.id} value={model.id}>
                                        {model.name}
                                    </option>
                                ))}
                        </Form.Select>
                    </Col>
                    {/* <Col className='cars-form-col'>
                        <Button className='cars-form-button' onClick={newModel}>
                            {showModelInput ? 'Cancel' : 'Add a model'}
                        </Button>
                    </Col> */}
                </Row>
                <Collapse in={showModelInput}>
                    <Row className='cars-form-row'>
                        <Col className='cars-form-col' xs={9}>
                            <Form.Group
                                className='mb-3 cars-form-input'
                                controlId='modelInput'
                            >
                                <Form.Label>New Model:</Form.Label>
                                <Form.Control
                                    type='text'
                                    placeholder='Enter the new model'
                                    value={newModelName}
                                    onChange={(e)=>{setNewModelName(e.target.value)}}
                                    className='cars-form-input-field'
                                />
                            </Form.Group>
                            <Button className='cars-form-button' onClick={()=>{console.log(newModelName)}}>
                                Add Model
                            </Button>
                        </Col>
                    </Row>
                </Collapse>
                <Row>
                    <Col className='cars-form-col'>
                        <Form.Group
                            className='mb-3 cars-form-input'
                            controlId='yearInput'
                        >
                            <Form.Label>Please enter the manufacturing year of the car:</Form.Label>
                            <Form.Control
                                type='number'
                                placeholder='Example: 2001'
                                onChange={handleYearChange}
                                className='cars-form-input-field'
                            />
                        </Form.Group>
                    </Col>
                    <Col className='cars-form-col'>
                        <Form.Select className='cars-form-select' onChange={handleColorChange}>
                            <option>Please choose the color of the car</option>
                            {colors.map((color) => (
                                <option
                                    key={color.id}
                                    value={color.id}
                                    style={{ color: color.name }}
                                >
                                    {color.name}
                                </option>
                            ))}
                        </Form.Select>
                    </Col>
                    
                </Row>
                <Row> 
                <Col className='cars-form-col'>
                        <Form.Group
                            className='mb-3 cars-form-input'
                            controlId='priceInput'
                        >
                            <Form.Label>Please enter the price of the car without configurations:</Form.Label>
                            <Form.Control
                                type='number'
                                placeholder='Example: 150 000'
                                onChange={handlePriceChange}
                                className='cars-form-input-field'
                            />
                        </Form.Group>
                    </Col>
                </Row>
                <Row>
                    <Col className='cars-form-col'>
                        <h2 className='cars-form-label'>
                            Please select the options for your car:
                        </h2>
                        {options.map((option) => (
                            <Form.Check
                                key={option.id}
                                type='checkbox'
                                id={option.id}
                                label={option.name}
                                checked={selectedOptions.includes(option.id)}
                                onChange={() => handleCheckboxChange(option.id)}
                                className='cars-form-checkbox'
                            />
                        ))}
                    </Col>
                </Row>
            </Container>
            <Button variant='primary' type='submit' className='cars-form-submit-button'>
                Save
            </Button>
        </Form>
    );
};

export default AddingCarsForm;
