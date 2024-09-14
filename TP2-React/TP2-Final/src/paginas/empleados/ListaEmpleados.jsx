import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import JSON_SERVER_URL from '../../json-server-config';
import BarraNavegacion from '../../componentes/BarraNavegacion';
// <Link to="/nuevo-empleado">Agregar Nuevo Empleado</Link>

import '../../styles/EmpleadoStyle.css'

const ListaEmpleados = () => {
    const [empleados, setEmpleados] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
      
        fetch(JSON_SERVER_URL)
            .then(response => response.json())
            .then(data => {
                setEmpleados(data);
                setLoading(false);
            })
            .catch(error => {
                console.error('Error fetching empleados:', error);
                setLoading(false);
            });
    }, []);

    if (loading) return <p>Cargando...</p>;

    return (
        <div>
            <BarraNavegacion></BarraNavegacion>
            <table className='EmpleadoTabla_contenedor'>
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
