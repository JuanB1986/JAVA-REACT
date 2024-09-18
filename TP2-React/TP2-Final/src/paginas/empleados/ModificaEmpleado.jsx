import React from 'react'
import { useState } from 'react';
import BarraNavegacion from '../../componentes/BarraNavegacion';
import JSON_SERVER_URL from '../../json-server-config';
import '../../styles/EmpleadoStyle.css'
import useForm from '../../hooks/useForm';
import nullInputValidation from '../../utils/validations';

const ModificaEmpleado = () => {

    const initialValues = { nombre: '', id: '', puesto: '' };
    const { values, errors, handleChange, handleSubmit } = useForm(initialValues, nullInputValidation);
    const [mensaje, setMensaje]=useState("");


    const mensajeFunction=(texto)=>{
        setMensaje(texto)
        const intervalo = setTimeout(() => {
          setMensaje("")
        }, 2000);
      }

    const buttonClick = () =>{
            handleSubmit().then(() => {
                if (Object.keys(errors).length === 0) {
                    const url_modify = JSON_SERVER_URL+'/'+values.id;
                    const requestOptions = {
                        method: 'PUT',
                        headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify({"nombre": values.nombre, "puesto": values.puesto })
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
                            mensajeFunction("Empleado modificado")
                        })
                        .catch(error => {
                            console.error('Hubo un problema con la solicitud:', error);
                            mensajeFunction("Error al modificar empleado")
                        });   
                    }else{mensajeFunction("Campos vacíos")}
                });
        };

    return  <div className='EmpleadoStyle_fondo'>
                <BarraNavegacion></BarraNavegacion>
                <div className='EmpleadoStyle_contenedor'>
                    <label className='EmpleadoStyle_label'>Id</label>
                    <br></br>
                    <input className='EmpleadoStyle_input' type="text" name='id' onChange={handleChange} value={values.id} />
                    <br></br>
                    <label className='EmpleadoStyle_label'>Nombre</label>
                    <br></br>
                    <input className='EmpleadoStyle_input' type="text" name='nombre' onChange={handleChange} value={values.nombre} />
                    <br></br>
                    <label className='EmpleadoStyle_label'>Puesto</label>
                    <br></br>
                    <input className='EmpleadoStyle_input' type="text" name='puesto' onChange={handleChange} value={values.puesto}/>
                    <br></br>
                    <button onClick={buttonClick} className='EmpleadoStyle_button'>Modificar</button>
                    <p className='EmpleadoStyle_mensaje'>{mensaje}</p>
                </div>
            </div>
};

export default ModificaEmpleado
