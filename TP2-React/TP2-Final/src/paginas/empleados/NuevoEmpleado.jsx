import React, { useState } from 'react';
import { useEffect } from 'react';
import JSON_SERVER_URL from '../../json-server-config';
import BarraNavegacion from '../../componentes/BarraNavegacion';


const NuevoEmpleado = () => {

    const [nombre, setNombre]=useState("");
    const [puesto, setPuesto]=useState("");
       
    const inputName=(event)=>{
        setNombre(event.target.value);
        console.log(nombre);
    }

    const inputPuesto=(event)=>{
        setPuesto(event.target.value);
        console.log(puesto);
    }

    const buttonClick = () =>{

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ "nombre": nombre, "puesto": puesto })
        };
        fetch(JSON_SERVER_URL, requestOptions)
            .then(response => response.json())
            .then(data => setPostId(data.id));    
    }


    return  <div>
                <BarraNavegacion></BarraNavegacion>
                <div className='EmpleadoStyle_contenedor'>                    
                    <label className='EmpleadoStyle_label'>Nombre</label>
                    <br></br>
                    <input className='EmpleadoStyle_input' type="text" required onChange={inputName} value={nombre} />
                    <br></br>
                    <label className='EmpleadoStyle_label'>Puesto</label>
                    <br></br>
                    <input className='EmpleadoStyle_input' type="text" required onChange={inputPuesto} value={puesto}/>
                    <br></br>
                    <button className='EmpleadoStyle_button' onClick={buttonClick} >Agregar</button>
                </div>
            </div>
};

export default NuevoEmpleado;