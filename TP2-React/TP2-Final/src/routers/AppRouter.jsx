import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import RutaPrivada from '../componentes/RutaPrivada';
import Login from '../paginas/Login';
import Home from '../paginas/Home';
import ListaEmpleados from '../paginas/empleados/ListaEmpleados';
import DetalleEmpleado from '../paginas/empleados/DetalleEmpleado';
import NuevoEmpleado from '../paginas/empleados/NuevoEmpleado';
import PageNotFound from '../error/PageNotFound';
import BajaEmpleado from '../paginas/empleados/BajaEmpleado';
import ModificaEmpleado from '../paginas/empleados/ModificaEmpleado';

const AppRouter = () => {
  return (   
    <Router>
        <Routes>
            <Route path="/iniciar-sesion" element={<Login />} />
            <Route path="/inicio" element={<RutaPrivada element={<Home />} />} />
            <Route path="/empleados/nuevo" element={<RutaPrivada element={<NuevoEmpleado />} />} />
            <Route path="/empleados/detalle" element={<RutaPrivada element={<DetalleEmpleado />} />} />
            <Route path="/empleados" element={<RutaPrivada element={<ListaEmpleados />} />} />
            <Route path="/empleados/baja" element={<RutaPrivada element={<BajaEmpleado />} />} />
            <Route path="/empleados/modifica" element={<RutaPrivada element={<ModificaEmpleado />} />} />
            <Route path="/" element={<Navigate to="/iniciar-sesion" />} />
            <Route path="*" element={<PageNotFound />} />
        </Routes>
    </Router> 
  )
}

export default AppRouter
