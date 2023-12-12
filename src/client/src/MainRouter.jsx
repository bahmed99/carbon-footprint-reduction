import React, { useEffect ,useState} from "react";
import { Routes, Route, Navigate } from "react-router-dom";

import FilterPage from './pages/FilterPage';
import Home from "./pages/Home";
import Page404 from "./pages/Page404";
import AddingCarsPage from "./pages/AddingCarsPage";
import RegisterPage from "./pages/RegisterPage";
import LoginPage from "./pages/LoginPage";
import BrandsPage from "./pages/BrandsPage";
import ModelsPage from "./pages/ModelsPage";
import StatesPage from "./pages/StatesPage";
import { AuthContext } from "./helpers/AuthContext";
import Navbar from "./components/Navbar";

export default function MainRouter() {

  const role = localStorage.getItem("role");
  const token = localStorage.getItem("accessToken");

  

  return (
    <div>
      <Navbar />
      <Routes>

        <Route path={"/vehicles"}  element={<PrivateRoute Component={FilterPage} auth={role == "USER" && token!==null} />}  />

        <Route path={"/addCars"} 
        element={<PrivateRoute Component={AddingCarsPage} auth={role === "ADMIN"} />} 
        />



        <Route path={"/brands"} element={<PrivateRoute Component={BrandsPage} auth={role == "ADMIN" && token!==null} />} />
        <Route path={"/models"}  element={<PrivateRoute Component={ModelsPage} auth={role == "ADMIN" && token!==null} />} />

        <Route path={"/"} element={<Home />} />

        <Route path={"/register"} element={<RegisterPage />} />
        <Route path={"/login"} element={<LoginPage />} />


        <Route path={"/states"}  element={<PrivateRoute Component={StatesPage} auth={role == "USER" && token!==null} />}  />



        <Route path="/404" element={<Page404 />} />
        <Route path="*" element={<Page404 />} />
      </Routes>
    </div>
  );
}

const PrivateRoute = ({ Component, auth }) => {


  return auth ? <Component /> : <Navigate to="/404" />;
};