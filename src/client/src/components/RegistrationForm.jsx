import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Alert from "react-bootstrap/Alert";

const RegistrationForm = () => {
    const [firstname, setFirstname] = useState('');
    const [lastname, setLastname] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmationPassword, setConfirmationPassword] = useState('');
    const [address, setAddress] = useState('');
    const [phone, setPhone] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        // Vérification des champs
        if (!firstname || !lastname || !email || !password || !address || !phone) {
            setError('Veuillez remplir tous les champs.');
            return;
        }
        if(password !== confirmationPassword) {
            setError('Les mots de passe ne correspondent pas.');
            return;
        }
        if(password.length < 6) {
            setError('Le mot de passe doit contenir au moins 8 caractères.');
            return;
        }
        if(!phone.match(/^0[1-9]([-. ]?[0-9]{2}){4}$/)) {
            setError('Le numéro de téléphone n\'est pas valide.');
            return;
        }
        setError(''); 
        console.log("Prénom : ", firstname);
        console.log("Nom : ", lastname);
        console.log("Email : ", email);
        console.log("Mot de passe : ", password);
        console.log("Adresse : ", address);
        console.log("Téléphone : ", phone);
    };

    return (
        <Form className='registration-form-container' onSubmit={handleSubmit}>
            {error && <Alert variant="danger" className='registration-form-error-alert'>{error}</Alert>}
            <Container>
                <Row className='registration-form-row'>
                    <Col className='registration-form-col' xs={6}>
                        <Form.Group className="mb-3 registration-form-input" controlId="firstnameInput">
                            <Form.Label>Prénom :</Form.Label>
                            <Form.Control type="text" placeholder="Saisissez votre prénom" value={firstname} onChange={(e) => setFirstname(e.target.value)} className='registration-form-input-field' />
                        </Form.Group>
                    </Col>
                    <Col className='registration-form-col' xs={6}>
                        <Form.Group className="mb-3 registration-form-input" controlId="lastnameInput">
                            <Form.Label>Nom :</Form.Label>
                            <Form.Control type="text" placeholder="Saisissez votre nom" value={lastname} onChange={(e) => setLastname(e.target.value)} className='registration-form-input-field' />
                        </Form.Group>
                    </Col>
                </Row>
                <Row className='registration-form-row'>
                    <Col className='registration-form-col'>
                        <Form.Group className="mb-3 registration-form-input" controlId="emailInput">
                            <Form.Label>Email :</Form.Label>
                            <Form.Control type="email" placeholder="Saisissez votre adresse email" value={email} onChange={(e) => setEmail(e.target.value)} className='registration-form-input-field' />
                        </Form.Group>
                    </Col>
                </Row>
                <Row className='registration-form-row'>
                    <Col className='registration-form-col'>
                        <Form.Group className="mb-3 registration-form-input" controlId="passwordInput">
                            <Form.Label>Mot de passe :</Form.Label>
                            <Form.Control type="password" placeholder="Saisissez votre mot de passe" value={password} onChange={(e) => setPassword(e.target.value)} className='registration-form-input-field' />
                        </Form.Group>
                    </Col>
                </Row>
                <Row className='registration-form-row'>
                <Col className='registration-form-col'>
                        <Form.Group className="mb-3 registration-form-input" controlId="passwordInput">
                            <Form.Label>Confirmation du mot de passe</Form.Label>
                            <Form.Control type="password" placeholder="Confirmez votre mot de passe" value={confirmationPassword} onChange={(e) => setConfirmationPassword(e.target.value)} className='registration-form-input-field' />
                        </Form.Group>
                    </Col>
                </Row>
                <Row className='registration-form-row'>
                <Col className='registration-form-col'>
                        <Form.Group className="mb-3 registration-form-input" controlId="addressInput">
                            <Form.Label>Adresse :</Form.Label>
                            <Form.Control type="text" placeholder="Saisissez votre adresse" value={address} onChange={(e) => setAddress(e.target.value)} className='registration-form-input-field' />
                        </Form.Group>
                    </Col>
                    <Col className='registration-form-col'>
                        <Form.Group className="mb-3 registration-form-input" controlId="phoneInput">
                            <Form.Label>Téléphone :</Form.Label>
                            <Form.Control type="tel" placeholder="Saisissez votre numéro de téléphone" value={phone} onChange={(e) => setPhone(e.target.value)} className='registration-form-input-field' />
                        </Form.Group>
                    </Col>
                </Row>
             
            </Container>
            <Button type="submit" className='registration-form-submit-button'>
                    S'inscrire
                </Button>
        </Form>
    );
};

export default RegistrationForm;
