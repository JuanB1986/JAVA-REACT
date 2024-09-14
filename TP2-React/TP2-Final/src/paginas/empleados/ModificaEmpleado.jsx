import React from 'react'
import { useState } from 'react';
import BarraNavegacion from '../../componentes/BarraNavegacion';
import JSON_SERVER_URL from '../../json-server-config';
import '../../styles/EmpleadoStyle.css'

const ModificaEmpleado = () => {
    const [nombre, setNombre]=useState("");
    const [puesto, setPuesto]=useState("");
    const [id, setId]=useState("");

    const url_modify = JSON_SERVER_URL+'/'+id;
        
    const inputName=(event)=>{
        setNombre(event.target.value);
    }

    const inputPuesto=(event)=>{
        setPuesto(event.target.value);
    }

    const inputId=(event)=>{
        setId(event.target.value);
    }

    const buttonClick = () =>{

        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({"nombre": nombre, "puesto": puesto })
        };
    
        fetch(url_modify, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la solicitud');
                }
                return response.json();
            })
            .then(data => {
                console.log('Respuesta del servidor:', data);
                setPostId(data.id);
            })
            .catch(error => {
                console.error('Hubo un problema con la solicitud:', error);
            });   
    }


    return  <div>
                <BarraNavegacion></BarraNavegacion>
                <div className='EmpleadoStyle_contenedor'>
                    <label className='EmpleadoStyle_label'>Id</label>
                    <br></br>
                    <input className='EmpleadoStyle_input' type="text" required onChange={inputId} value={id} />
                    <br></br>
                    <label className='EmpleadoStyle_label'>Nombre</label>
                    <br></br>
                    <input className='EmpleadoStyle_input' type="text" required onChange={inputName} value={nombre} />
                    <br></br>
                    <label className='EmpleadoStyle_label'>Puesto</label>
                    <br></br>
                    <input className='EmpleadoStyle_input' type="text" required onChange={inputPuesto} value={puesto}/>
                    <br></br>
                    <button onClick={buttonClick} className='EmpleadoStyle_button'>Modificar</button>
                </div>
            </div>
};

export default ModificaEmpleado
