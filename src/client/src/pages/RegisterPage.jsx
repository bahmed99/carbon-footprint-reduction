import React from "react";
import { Link } from "react-router-dom";
import RegistrationForm from "../components/RegistrationForm";
import LogoImg from "../assets/images/logo2.png";

const RegisterPage = () => {
    return (
        <div className="register-page-container">
        <img  className="register-page-logo" src={LogoImg} alt="Logo" />
        <RegistrationForm />
        <h2 className="already-registred"> Vous êtes déjà inscrit ? <Link to="/login" className="link-to-login" > Connectez-vous ici </Link> </h2>
        </div>
    );
}

export default RegisterPage;