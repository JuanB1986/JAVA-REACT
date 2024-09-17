import React, { useState } from 'react';
import JSON_SERVER_URL from '../../json-server-config';
import BarraNavegacion from '../../componentes/BarraNavegacion';
import nullInputValidation from '../../utils/validations';
import useForm from '../../hooks/useForm';

const NuevoEmpleado = () => {
    
    const initialValues = { input: '' };
    const { values, errors, handleChange, handleSubmit } = useForm(initialValues, nullInputValidation);

    const buttonClick = () =>{
        handleSubmit().then(() => {
            if (Object.keys(errors).length === 0) {

                const requestOptions = {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ "nombre": values.nombre, "puesto": values.puesto })
                };
                
                fetch(JSON_SERVER_URL, requestOptions)
                .then(response => {
                    if (response.ok) {
                        console.log('Empleado agregado');
                        return response.json();
                    } else {
                        console.error('Error al agregar el empleado');
                    }
                })
                .catch(error => console.error('Error:', error));  
            }
        });
    }


    return  <div>
                <BarraNavegacion></BarraNavegacion>
                <div className='EmpleadoStyle_contenedor'>                    
                    <label className='EmpleadoStyle_label'>Nombre</label>
                    <br></br>
                    <input className='EmpleadoStyle_input' type="text" name='nombre' onChange={handleChange} value={values.nombre} />
                    <br></br>
                    <label className='EmpleadoStyle_label'>Puesto</label>
                    <br></br>
                    <input className='EmpleadoStyle_input' type="text" name='puesto' onChange={handleChange} value={values.puesto}/>
                    <br></br>
                    <button className='EmpleadoStyle_button' onClick={buttonClick} >Agregar</button>
                </div>
            </div>
};

export default NuevoEmpleado;