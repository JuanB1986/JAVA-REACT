import React, { useEffect, useState } from 'react';
import JSON_SERVER_URL from '../../json-server-config';
import BarraNavegacion from '../../componentes/BarraNavegacion';
import '../../styles/EmpleadoStyle.css'

const ListaEmpleados = () => {
    const [empleados, setEmpleados] = useState([]);

    useEffect(() => {
      
        fetch(JSON_SERVER_URL)
            .then(response => response.json())
            .then(data => {
                setEmpleados(data);
            })
            .catch(error => {
                console.error('Error fetching empleados:', error);
            });
    }, []);

    return (
        <div className='EmpleadoStyle_fondo'>
            <BarraNavegacion></BarraNavegacion>
            <table className='EmpleadoTabla_tabla'>
                <thead>
                    <tr className='EmpleadoTabla_row'>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Puesto</th>
                    </tr>
                </thead>    
                <tbody>  
                    {
                        empleados.map( (m, index) =>(<tr key={index}>
                            <td className='EmpleadoTabla_rd'>{m.id}</td>
                            <td className='EmpleadoTabla_rd'>{m.nombre}</td>
                            <td className='EmpleadoTabla_rd'>{m.puesto}</td>
                        </tr>))
                    }
                </tbody> 
            </table>    
        </div>
    );
};

export default ListaEmpleados;
