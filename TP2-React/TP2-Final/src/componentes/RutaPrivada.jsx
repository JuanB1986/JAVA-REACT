import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAutenticacion } from './ContextoAutenticacion';

const RutaPrivada = ({ element }) => {
    const { isAuthenticated } = useAutenticacion();
    return isAuthenticated ? element : <Navigate to="/iniciar-sesion" />;
};

export default RutaPrivada;