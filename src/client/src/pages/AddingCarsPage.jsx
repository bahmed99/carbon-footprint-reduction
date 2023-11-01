import React from 'react';
import AddingCarsForm from '../components/AddingCarsForm';
import Carpage from '../assets/images/carpage.png'
const AddingCarsPage = () => {
    return (
        <div className='adding-cars-page-container'>
            <img className='adding-cars-page-img' src={Carpage} alt='carpage' />
            <h1 className='adding-cars-page-title'>Welcome to our vehicle registration form.</h1>
<h3 className='adding-cars-page-subtitle'>Please fill out the fields below to register your car.</h3>

            <AddingCarsForm />
        </div>
    );
};

export default AddingCarsPage;
