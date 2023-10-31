import React from "react";
import { Link } from "react-router-dom";
import LogoImg from "../assets/images/logo2.png";
import LoginForm from "../components/LoginForm";

const LoginPage = () => {
    return (
        <div className="register-page-container">
        <img  className="register-page-logo" src={LogoImg} alt="Logo" />
        <LoginForm />
        <h2 className="already-registred"> Vous n'êtes pas encore inscrit ? <Link to="/register" className="link-to-login" > Inscrivez-vous ici </Link> </h2>
        </div>
    );
}

export default LoginPage;