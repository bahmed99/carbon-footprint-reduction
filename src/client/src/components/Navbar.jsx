import React, { useContext } from "react";

import LogoImg from "../assets/images/logo2.png";
import UserImage from "../assets/images/User_icon.png";
import { AuthContext } from "../helpers/AuthContext";
import axios from "axios";


function Navbar() {
  const { authState,setAuthState } = useContext(AuthContext);
  const Authorization = 'Bearer' + localStorage.getItem('accessToken');
  const token = localStorage.getItem("accessToken");




  const logout = () => {
    
    console.log("logout cliqued")
    axios
      .post(
        process.env.REACT_APP_API_URL + "auth/logout",
        {
          "token": token,
        },
        {
          headers: {
            Authorization: Authorization,
          },
        }
      )
      .then((response) => {
        if (response.data.error) {
          // TODO gérer les erreurs de déconnexion ici
          console.log(response.data.error, "error : logout failed !")
          
        } else {
          setAuthState({
            connected: false,
          });
          localStorage.removeItem("accessToken");
          window.location = "/";
        }
      })
      .catch((error) => {
        // TODO Gérer les erreurs de déconnexion ici
      });
  };
  
  return (
    <div className="nav">
      <div className="div-navbar-container">
        <div className="div-left-container">
          <a href="/">
            <img className="nav-logo" src={LogoImg} alt="Logo" />
          </a>
        </div>
        <div className="div-right-container">
          <div className="div-navbar-link-container">
            <a className="nav-link" href="/">
                Home
            </a>
            {authState.connected ? (
              <>
                <a className="nav-link" href="/vehicles">
                  Vehicles
                </a>
                <a className="nav-link" href="/addCars">
                  Add vehicle
                </a>
                <div className="nav-sign-container">
                  <div className="user-container">
                    <img src={UserImage} alt="User" className="user-image" />
                    <span className="down-arrow">&#8964;</span>
                    <div className="dropdown-content-profile">
                      <a className="nav-link" href="/profile">
                        Username
                      </a>
                      <button className="logout-button" onClick={logout}>
                        Logout
                      </button>
                    </div>
                  </div>
                </div>
              </>
            ) : (
              <div className="nav-sign-container">
                <a className="nav-link-login" href="/login">
                  Login
                </a>
                <a className="nav-link-login" href="/register">
                  Register
                </a>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Navbar;