import React from 'react';
import backgroundImage from '../assets/images/vehicules.jpg';
import Navbar from "../components/Navbar";

const Home = () => {
  return (
    <div >
      <Navbar />
      <div className="home-background-img" style={{ backgroundImage: `url(${backgroundImage})` }}>
        <div className="home-background-overlay">
          <div className="home-background-content">
            <h1 className='home-h1'>Welcome to our application</h1>
            <p className='home-p'>In this application, you can filter to find your new car or add a new car to the database.</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
