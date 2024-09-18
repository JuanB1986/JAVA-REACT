import BarraNavegacion from '../../componentes/BarraNavegacion';
import React, { useState } from 'react';
import JSON_SERVER_URL from '../../json-server-config';
import nullInputValidation from '../../utils/validations';
import useForm from '../../hooks/useForm';


const DetalleEmpleado = () => {

    const [empleado, setEmpleado] = useState([]);
    const initialValues = { id: '' };
    const { values, errors, handleChange, handleSubmit } = useForm(initialValues, nullInputValidation);
    const [mensaje, setMensaje]=useState("");

    const mensajeFunction=(texto)=>{
        setMensaje(texto)
        const intervalo = setTimeout(() => {
          setMensaje("")
        }, 2000);
      }

    const buttonClick = async () => {

        handleSubmit().then(() => {
          if (Object.keys(errors).length === 0) {
            const url_modify = JSON_SERVER_URL+'/'+values.id;
            fetch(url_modify)
                .then(response => response.json())
                .then(data => {
                    setEmpleado(data);
                })
                .catch(error => {
                    console.error('Error al obtener el empleado:', error);
                    mensajeFunction('Error al obtener el empleado:')
                });
           }else{mensajeFunction('Campos vacíos')}   //imprime que el id es campo requerido.
        });
    };

    return  <div className='EmpleadoStyle_fondo'>
                <BarraNavegacion></BarraNavegacion>
                <div className='EmpleadoStyle_contenedor'>        
                    <label className='EmpleadoStyle_label'> Consultar por id: </label>
                    <input className='EmpleadoStyle_input' type='text' name='id' onChange={handleChange} value={values.id}></input>
                    <br></br>
                    <button className='EmpleadoStyle_button' onClick={buttonClick}>Consultar</button>
                    <p className='EmpleadoStyle_mensaje'>{mensaje}</p>
                </div>
                <hr></hr>
                <table className='EmpleadoTabla_tabla'>
                    <thead>
                        <tr className='EmpleadoTabla_row'>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Puesto</th>
                        </tr>
                    </thead>    
                    <tbody>  
                        <td className='EmpleadoTabla_rd'>{empleado.id}</td>
                        <td className='EmpleadoTabla_rd'>{empleado.nombre}</td>
                        <td className='EmpleadoTabla_rd'>{empleado.puesto}</td>
                    </tbody> 
                </table> 
                
            </div>
};

export default DetalleEmpleado;
