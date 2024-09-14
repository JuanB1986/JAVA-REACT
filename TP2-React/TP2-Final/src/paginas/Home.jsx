import React from 'react';
import BarraNavegacion from '../componentes/BarraNavegacion';
import '../styles/HomeStyle.css'

const Home = () => {
    return (
        <div >
            <BarraNavegacion></BarraNavegacion>         
            <div id='EmpleadoStyle_home_contenedor'>

            </div>
            <footer id='EmpleadoStyle_home_footer'>
               <h3 id='EmpleadoStyle_home_h3'>Trabajo Práctico React</h3>
            </footer >
        </div>
    );
};

export default Home;

