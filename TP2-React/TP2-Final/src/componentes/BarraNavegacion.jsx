import React from 'react'
import '../styles/BarraNav.css'
import { useAutenticacion } from './ContextoAutenticacion'
import { useNavigate } from 'react-router-dom';

const BarraNavegacion = () => {

  const navigate = useNavigate();
  const {logout} = useAutenticacion();

  const buttonCerrarSesion=()=>{
    logout();  
  }

  const handleNavigate = (path) => {
    navigate(path); 
  };

  return (
    <div id='BarraNav_contenedor'>
      <h2 id='BarraNav_titulo'>Sistema de control de empleados</h2>
      <button className='BarraNav_button' onClick={() => handleNavigate('/inicio')}>Home</button>
      <button className='BarraNav_button' onClick={() => handleNavigate('/empleados/detalle')}>Detalle</button>
      <button className='BarraNav_button' onClick={() => handleNavigate('/empleados')}>Listado</button>
      <button className='BarraNav_button' onClick={() => handleNavigate('/empleados/nuevo')}>Nuevo</button>
      <button className='BarraNav_button' onClick={() => handleNavigate('/empleados/baja')}>Eliminar</button>
      <button className='BarraNav_button' onClick={() => handleNavigate('/empleados/modifica')}>Modificar</button>
      <button className='BarraNav_button' onClick={buttonCerrarSesion}>Cerrar sesion</button>
    </div>
  )
}

export default BarraNavegacion
