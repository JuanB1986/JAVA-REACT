import React, { useState } from 'react'
import BarraNavegacion from '../../componentes/BarraNavegacion';
import JSON_SERVER_URL from '../../json-server-config';
import '../../styles/EmpleadoStyle.css'

const BajaEmpleado = () => {

    const [inpudData, setInputData]=useState("");

    const url_delete = JSON_SERVER_URL+'/'+inpudData;

  
    const buttonClick = () =>{

      console.log(url_delete);

        const requestOptions = {
          method: 'DELETE',
          headers: { 'Content-Type': 'application/json' },
        };
        fetch(url_delete, requestOptions)

            .then(response => {
              if (response.ok) {
                console.log('Recurso eliminado con éxito');
                return response.json(); // Si necesitas manejar la respuesta
              } else {
                console.error('Error al eliminar el recurso');
              }
            })
            .catch(error => console.error('Error:', error));
    }

    const inputHandler = (event) =>{
      setInputData(event.target.value);        
    }

  return (
    <div >
      <BarraNavegacion></BarraNavegacion>
      <div className='EmpleadoStyle_contenedor'>        
        <label className='EmpleadoStyle_label'> Eliminar por id: </label>
        <input className='EmpleadoStyle_input' type='text' onChange={inputHandler} value={inpudData}></input>
        <br></br>
        <button className='EmpleadoStyle_button' onClick={buttonClick}>Eliminar</button>
      </div>
    </div>
  )
}

export default BajaEmpleado
