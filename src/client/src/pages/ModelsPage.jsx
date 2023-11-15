import React, { useState, useEffect } from "react";
import Axios from 'axios';
import Navbar from '../components/Navbar';
import { useNavigate } from 'react-router-dom';
import Button from 'react-bootstrap/Button';
import Collapse from 'react-bootstrap/Collapse';
import Form from 'react-bootstrap/Form';
import Swal from 'sweetalert2'
import Alert from 'react-bootstrap/Alert';
import ReactLoading from "react-loading";

export default function ModelsPage() {
    const [data, setData] = useState([]);
    const [modelAddingError, setModelAddingError] = useState('');
    const [newBrandNames, setNewBrandNames] = useState([]);
    const [showModelInputs, setShowModelInputs] = useState([]);
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const Authorization = 'Bearer' + localStorage.getItem('accessToken');
    const updateNewBrandName = (index, value) => {
        setNewBrandNames((prevNames) => {
          const newNames = [...prevNames];
          newNames[index] = value;
          return newNames;
        });
      };
    
      const toggleShowModelInput = (index) => {
        setShowModelInputs((prevInputs) => {
          const newInputs = [...prevInputs];
          newInputs[index] = !newInputs[index];
          return newInputs;
        });
      };

    const submitAddBrand = (e,index) => {
        e.preventDefault();
        setLoading(true);
        if (newBrandNames[index] === '') {
            setModelAddingError('Please enter a model name');
            return;
        }
        setModelAddingError('');
        const json = {
            "name": newBrandNames[index],
            "brandId" : index + 1
        };
        Axios.post(process.env.REACT_APP_API_URL + 'models', json, {
            headers: {
                Authorization: Authorization
            }
        }).then((response) => {
            setLoading(false);
            Swal.fire({
                title: 'Model added successfully , you can now add vehicles to this model',
                icon: 'success',
                confirmButtonText: 'Go to the add vehicle page'
            }).then(() => {
                navigate('/addCars');
            })
        }).catch((error) => {
            setLoading(false);
            Swal.fire({
                title: 'Error',
                text: 'Error while adding the model',
                icon: 'error',
                confirmButtonText: 'OK'
            })
        });
    };


    useEffect(() => {
        Axios.get(process.env.REACT_APP_API_URL + 'vehicles/countByModelName', {
            headers: {
                Authorization: Authorization
            }
        }).then((response) => {
            console.log(response);
            console.log(response.data);
            setData(response.data);
            setNewBrandNames(response.data.map(() => ''));
            setShowModelInputs(response.data.map(() => false));
        });
    }, []);
    return (
        <>
            <Navbar />
            <div className='models-page-container'>
                <h2 className='models-page-title'>You can find here the various models for each brand in our database.</h2>
                <div className='models-page-list'>
                    {data.map((item, index) => (
                        <div key={index} className="brand-models-container">
                            <h2 className="brand-name">{item.brandName}</h2>

                            {item.models.length === 0 ? (
                                <p>This brand doesn't have models yet, you can add one by clicking on the button below.</p>
                            ) : (
                                <>
                                    {item.models.map((model, Modelindex) => (
                                        <div key={index} className='models-page-item'>
                                            <div className='models-page-item-name' >{model.modelName}</div>
                                            <div className='models-page-item-count'>{model.vehicleCount}</div>
                                        </div>
                                    ))}
                                    <p> You can always add a new model to this brand by clicking on the button below.</p>
                                </>

                            )}

                            <div className='adding-model-container'>
                                <Button className='adding-model-button' onClick={() => toggleShowModelInput(index)} >
                                    {showModelInputs[index] ? 'Cancel' : 'Add a model'}
                                </Button>
                                {modelAddingError && (
                                    <Alert variant='danger' className='brand-form-error-alert'>
                                        {modelAddingError}
                                    </Alert>
                                )}
                                <Collapse in={showModelInputs[index]}>
                                    <Form className='adding-model-form' onSubmit={(e) => submitAddBrand(e, index)}>
                                        <Form.Group
                                            className='mb-3 cars-form-input'
                                            controlId='modelInput'
                                        >

                                            <Form.Control
                                                type='text'
                                                placeholder='Enter the new brand'
                                                value={newBrandNames[index]}
                                                onChange={(e) => updateNewBrandName(index, e.target.value)}
                                                className='adding-model-input-field'
                                            />
                                        </Form.Group>
                                        <Button className='adding-model-button' type='submit'>
                                            Add Model
                                        </Button>
                                    </Form>



                                </Collapse>
                                <ReactLoading type={"spin"} color={"#EF7C00"} height={50} width={50} hidden={!loading} />
                            </div>
                        </div>
                    ))}

                </div>

            </div>
        </>
    );
}