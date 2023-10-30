import React, { useState , useRef } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Collapse from 'react-bootstrap/Collapse';
import Alert from "react-bootstrap/Alert";

const marques = [
    { id: 1, name: 'marque1' },
    { id: 2, name: 'marque2' },
    { id: 3, name: 'marque3' },
    { id: 4, name: 'marque4' },
    { id: 5, name: 'marque5' },
];

const modeles = [
    { id: 1, name: 'modele1' },
    { id: 2, name: 'modele2' },
    { id: 3, name: 'modele3' },
    { id: 4, name: 'modele4' },
    { id: 5, name: 'modele5' },
];

const colors = [
    { id: 1, name: 'red' },
    { id: 2, name: 'blue' },
    { id: 3, name: 'black' },
    { id: 4, name: 'white' },
    { id: 5, name: 'grey' },
];

const options = [
    { id: 1, name: 'option1' },
    { id: 2, name: 'option2' },
    { id: 3, name: 'option3' },
    { id: 4, name: 'option4' },
    { id: 5, name: 'option5' },
]


const AddingCarsForm = () => {
    const [marque, setMarque] = useState('');
    const [modele, setModele] = useState('');
    const [annee, setAnnee] = useState('');
    const [couleur, setCouleur] = useState('');
    const [showMarqueInput, setShowMarqueInput] = useState(false);
    const [showModeleInput, setShowModeleInput] = useState(false);
    const [selectedOptions, setSelectedOptions] = useState([]);
    const [error, setError] = useState('');
    const errorRef = useRef(null);
    const nouvellMarque = () => {
        setMarque('');
        setShowMarqueInput(!showMarqueInput);
    }

    const nouveauModele = () => {
        setModele('');
        setShowModeleInput(!showModeleInput);
    }

    const handleCheckboxChange = (optionId) => {
        if (selectedOptions.includes(optionId)) {
            setSelectedOptions(prevOptions => prevOptions.filter(id => id !== optionId));
        } else {
            setSelectedOptions(prevOptions => [...prevOptions, optionId]);
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Vérification des champs
        if (!marque || !modele || !annee || !couleur) {
            setError('Veuillez remplir tous les champs.');
            errorRef.current?.scrollIntoView({ behavior: "smooth" });
            return;
        }
        setError(''); 
        console.log("marque : ", marque);
        console.log("modele : ", modele);
        console.log("annee : ", annee);
        console.log("couleur : ", couleur);
        console.log("options sélectionnées : ", selectedOptions);

    };
    const handleMarqueChange = (e) => {
        setMarque(e.target.value);
    };

    const handleModeleChange = (e) => {
        setModele(e.target.value);
    }

    const handleAnneeChange = (e) => {
        setAnnee(e.target.value);
    }
    const handleCouleurChange = (e) => {
        setCouleur(e.target.value);
    }

    return (
        <Form className='cars-form-container' onSubmit={handleSubmit} >
            <div ref={errorRef} />
            {error && <Alert variant="danger" className='cars-form-error-alert'>{error}</Alert>}
            <Container>
                <Row className='cars-form-row'>
                    <Col className='cars-form-col' xs={9}>

                        <Form.Select className='cars-form-select' onChange={handleMarqueChange} value={marque} >
                            <option value="" >{showMarqueInput ? 'Veuillez saisir la nouvelle de la voiture' : 'Veuillez choisir la marque de la voiture'}</option>
                            {!showMarqueInput &&
                                <>
                                    {marques.map((marque) => (
                                        <option key={marque.id} value={marque.name}>
                                            {marque.name}
                                        </option>
                                    ))
                                    }
                                </>
                            }
                        </Form.Select>
                    </Col>
                    <Col className='cars-form-col'>
                        <Button className='cars-form-button' onClick={nouvellMarque}> {showMarqueInput ? 'Annuler' : 'Ajouter une marque'} </Button>
                    </Col>
                </Row>
                <Collapse in={showMarqueInput}>
                    <Row className='cars-form-row'>
                        <Col className='cars-form-col' xs={9}>
                            <Form.Group className="mb-3 cars-form-input" controlId="marqueInput">
                                <Form.Label>Nouvelle Marque :</Form.Label>
                                <Form.Control type="text" placeholder="Saisissez la nouvelle marque" value={marque} onChange={handleMarqueChange} className='cars-form-input-field' />
                            </Form.Group>
                        </Col>
                    </Row>
                </Collapse>
                <Row className='cars-form-row'>
                    <Col className='cars-form-col' xs={9}>
                        <Form.Select className='cars-form-select' onChange={handleModeleChange} value={modele}>
                            <option value="">{showModeleInput ? 'Veuillez saisir le nouveau modèle de la voiture' : 'Veuillez choisir le modèle de la voiture'}</option>
                            {!showModeleInput &&
                                <>
                                    {modeles.map((modele) => (
                                        <option key={modele.id} value={modele.name}>
                                            {modele.name}
                                        </option>
                                    ))
                                    }
                                </>
                            }
                        </Form.Select>
                    </Col>

                    <Col className='cars-form-col'>
                        <Button className='cars-form-button' onClick={nouveauModele}> {showModeleInput ? 'Annuler' : 'Ajouter un modèle'} </Button>
                    </Col>
                </Row>
                <Collapse in={showModeleInput}>
                    <Row className='cars-form-row'>
                        <Col className='cars-form-col' xs={9}>
                            <Form.Group className="mb-3 cars-form-input" controlId="modeleInput">
                                <Form.Label>Nouveau modèle :</Form.Label>
                                <Form.Control type="text" placeholder="Saisissez la nouvelle marque" value={modele} onChange={handleModeleChange} className='cars-form-input-field' />
                            </Form.Group>
                        </Col>
                    </Row>
                </Collapse>

                <Row>
                    <Col className='cars-form-col'>
                        <Form.Group className="mb-3 cars-form-input" controlId="formBasicEmail">
                            <Form.Label>Veuillez saisir l'année de fabrication de la voiture :</Form.Label>
                            <Form.Control type="number" placeholder="Exemple : 2001" onChange={handleAnneeChange} className='cars-form-input-field' />
                        </Form.Group>
                    </Col>
                    <Col className='cars-form-col'>

                        <Form.Select className='cars-form-select' onChange={handleCouleurChange}  >
                            <option>Veuillez choisir la couleur de la voiture</option>

                            {colors.map((color) => (
                                <option key={color.id} value={color.id} style={{ color: color.name }}
                                >
                                    {color.name}
                                </option>

                            ))}

                        </Form.Select>
                    </Col>
                </Row>
                <Row>
                </Row>

                
                <Row>
                    
                <Col className='cars-form-col'>
                <h2 className='cars-form-label'> Veuillez sélectionner les options pour votre voiture : </h2>
            {options.map((option) => (
                <Form.Check
                    key={option.id}
                    type="checkbox"
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
            <Button variant="primary" type="submit" className='cars-form-submit-button'>
                    Enregistrer
                </Button>
        

        </Form>
    );
};

export default AddingCarsForm;
