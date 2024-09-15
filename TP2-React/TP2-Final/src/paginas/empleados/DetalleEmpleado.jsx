import BarraNavegacion from '../../componentes/BarraNavegacion';
import React, { useState } from 'react';
import JSON_SERVER_URL from '../../json-server-config';

const DetalleEmpleado = () => {

    const [empleado, setEmpleado] = useState([]);
    const [error, setError] = useState(false);
    const [inputData, setInputData]=useState("");

    
    const url_modify = JSON_SERVER_URL+'/'+inputData;

    const buttonClick = async () => {

        try {
            const response = await fetch(url_modify);
            if (!response.ok) {
                throw new Error('Error al obtener los datos');
            }
            const data = await response.json();
            setEmpleado(data);
        } catch (err) {
            setError(err.message);
        } 
    };

    const inputHandler = (event) =>{
        setInputData(event.target.value);     
    }


    return  <div>
                <BarraNavegacion></BarraNavegacion>
                <div className='EmpleadoStyle_contenedor'>        
                    <label className='EmpleadoStyle_label'> Consultar por id: </label>
                    <input className='EmpleadoStyle_input' type='text' onChange={inputHandler} value={inputData}></input>
                    <br></br>
                    <button className='EmpleadoStyle_button' onClick={buttonClick}>Consultar</button>
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
