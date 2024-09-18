import React from 'react';
import BarraNavegacion from '../componentes/BarraNavegacion';
import '../styles/HomeStyle.css'

const Home = () => {
    return (
        <div className='EmpleadoStyle_home_contenedor'>
            <BarraNavegacion></BarraNavegacion>    
            <div id='EmpleadoStyle_home_titulo'>
                <h1>
                    Bienvenidos al sistema de gestión de empleados.
                </h1>
            </div>
        </div>
    );
};

export default Home;

