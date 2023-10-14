import React from 'react'
import { Routes, Route } from "react-router-dom";
import Test from './pages/Test'

export default function MainRouter() {
  return (
    <div>
        <Routes>
            <Route path={"/"} element={<Test/>} />
        </Routes>
    </div>
  )
}
