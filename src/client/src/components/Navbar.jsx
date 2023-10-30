import React, { useState } from "react";

import LogoImg from "../assets/images/logo2.png";
import UserImage from "../assets/images/User_icon.png";

function Navbar() {
  const [isConnected, setIsConnected] = useState(false);

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
            {isConnected ? (
              <>
                <a className="nav-link" href="/filtre">
                  Dashboard
                </a>
                <a className="nav-link" href="/insertion">
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
                      <a className="nav-link" href="/logout">
                        Logout
                      </a>
                    </div>
                  </div>
                </div>
              </>
            ) : (
              <div className="nav-sign-container">
                <a className="nav-link-login" href="/login">
                  Login
                </a>
                <a className="nav-link-login" href="/login">
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