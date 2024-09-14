import React from 'react';
import BarraNavegacion from '../../componentes/BarraNavegacion';
import { useParams } from 'react-router-dom';

const DetalleEmpleado = () => {
    const { id } = useParams();

    return <div>
        <BarraNavegacion></BarraNavegacion>
    </div>
};

export default DetalleEmpleado;