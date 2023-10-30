import React from 'react'
import { Routes, Route } from "react-router-dom";
import Test from './pages/Test'
import FiltrePage from './pages/FiltrePage';

export default function MainRouter() {
  return (
    <div>
        <Routes>
            <Route path={"/"} element={<Test/>} />
            <Route path={"/filtre"} element={<FiltrePage/>} />
        </Routes>
    </div>
  )
}
