import React from "react";
import { Routes, Route } from "react-router-dom";

import FiltrePage from './pages/FiltrePage';
import Home from "./pages/Home";
import Page404 from "./pages/Page404";
import AddingCarsPage from "./pages/AddingCarsPage";

export default function MainRouter() {
  return (
    <div>
      <Routes>
        <Route path={"/filtre"} element={<FiltrePage />} />
        <Route path={"/addCars"} element={<AddingCarsPage/>} />
        <Route path={"/"} element={<Home />} />
        <Route path="/404" element={<Page404 />} />
        <Route path="*" element={<Page404 />} />
      </Routes>
    </div>
  );
}
