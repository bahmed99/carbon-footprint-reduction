import React from 'react'
import { Routes, Route } from "react-router-dom";
import Test from './pages/Test'
import AddingCarsPage from './pages/AddingCarsPage';
export default function MainRouter() {
  return (
    <div>
        <Routes>
            <Route path={"/"} element={<Test/>} />
            <Route path={"/addCars"} element={<AddingCarsPage/>} />
        </Routes>
    </div>
  )
}
