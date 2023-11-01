import React, { useState,useContext } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';
import Container from 'react-bootstrap/Container';
import Alert from 'react-bootstrap/Alert';
import axios from 'axios';
import { AuthContext } from '../helpers/AuthContext';

const LoginForm = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const { setAuthState } = useContext(AuthContext);

    const handleSubmit = (e) => {
        e.preventDefault();
        // Vérification des champs
        if (!email || !password) {
            setError('Veuillez saisir votre email et votre mot de passe.');
            return;
        }
        // Logique de connexion ici
        setError('');
        console.log("Email : ", email);
        console.log("Mot de passe : ", password);

        // Envoi des données au serveur
        const data = {
            email: email,
            password: password
        };
        axios.post('http://localhost:8080/auth/login', data)
            .then((response) => {
                if (response.data.error) {
                    setError(response.data.error);
                    alert(response.data.error);
                } else {
                    setAuthState({
                        connected: true
                    });
                    localStorage.setItem('accessToken', response.data);
                    window.location = '/';
                }
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <Form className='registration-form-container' onSubmit={handleSubmit}>
            {error && <Alert variant="danger" className='login-form-error-alert'>{error}</Alert>}
            <Container>
                <Form.Group className="mb-3  registration-form-input" controlId="emailInput">
                    <Form.Label>Email :</Form.Label>
                    <Form.Control type="email" placeholder="Saisissez votre adresse email" value={email} onChange={(e) => setEmail(e.target.value)} />
                </Form.Group>

                <Form.Group className="mb-3  registration-form-input" controlId="passwordInput">
                    <Form.Label>Mot de passe :</Form.Label>
                    <Form.Control type="password" placeholder="Saisissez votre mot de passe" value={password} onChange={(e) => setPassword(e.target.value)} />
                </Form.Group>

       
            </Container>
            <Button type="submit" className='registration-form-submit-button'>
                    Se connecter
                </Button>
        </Form>
    );
};

export default LoginForm;
