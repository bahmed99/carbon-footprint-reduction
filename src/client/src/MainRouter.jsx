import React from "react";
import { Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import Page404 from "./pages/Page404";
import Test from "./pages/Test";

export default function MainRouter() {
  return (
    <div>
      <Navbar />
      <Routes>
        <Route path={"/"} element={<Home />} />


        <Route path="/404" element={<Page404 />} />
        <Route path="*" element={<Page404 />} />
      </Routes>
    </div>
  );
}
