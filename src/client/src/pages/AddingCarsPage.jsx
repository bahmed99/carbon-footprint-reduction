import React from 'react';
import AddingCarsForm from '../components/AddingCarsForm';
import Carpage from '../assets/images/carpage.png'
const AddingCarsPage = () => {
    return (
        <div className='adding-cars-page-container'>
            <img className='adding-cars-page-img' src={Carpage} alt='carpage' />
            <h1 className='adding-cars-page-title'>Bienvenue sur notre formulaire d'enregistrement des véhicules.  </h1> 
            <h3 className='adding-cars-page-subtitle'> Merci de remplir les champs ci-dessous pour enregistrer votre voiture. </h3>
            <AddingCarsForm />
        </div>
    );
};

export default AddingCarsPage;
