import React from "react";
import { Routes, Route } from "react-router-dom";

import FilterPage from './pages/FilterPage';
import Home from "./pages/Home";
import Page404 from "./pages/Page404";
import AddingCarsPage from "./pages/AddingCarsPage";
import RegisterPage from "./pages/RegisterPage";
import LoginPage from "./pages/LoginPage";
import BrandsPage from "./pages/BrandsPage";
import ModelsPage from "./pages/ModelsPage";
import StatesPage from "./pages/StatesPage";
export default function MainRouter() {
  return (
    <div>
      <Routes>

        <Route path={"/vehicles"} element={<FilterPage />} />
        <Route path={"/addCars"} element={<AddingCarsPage/>} />
        <Route path={"/brands"} element={<BrandsPage/>} />
        <Route path={"/models"} element={<ModelsPage/>} />
        <Route path={"/"} element={<Home />} />
        <Route path={"/register"} element={<RegisterPage />} />
        <Route path={"/login"} element={<LoginPage />} />
        <Route path={"/states"} element={<StatesPage />} />
        <Route path="/404" element={<Page404 />} />
        <Route path="*" element={<Page404 />} />
      </Routes>
    </div>
  );
}
