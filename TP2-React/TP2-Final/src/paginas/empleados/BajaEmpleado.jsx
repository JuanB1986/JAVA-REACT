import BarraNavegacion from '../../componentes/BarraNavegacion';
import JSON_SERVER_URL from '../../json-server-config';
import '../../styles/EmpleadoStyle.css'
import nullInputValidation from '../../utils/validations';
import useForm from '../../hooks/useForm';
import { useState } from 'react';

const BajaEmpleado = () => {
  
    const initialValues = { id: '' };
    const { values, errors, handleChange, handleSubmit } = useForm(initialValues, nullInputValidation);
    const [mensaje, setMensaje]=useState("");

    const mensajeFunction=(texto)=>{
      setMensaje(texto)
      const intervalo = setTimeout(() => {
        setMensaje("")
      }, 2000);
    }

    const buttonClick = () => {    
      
      handleSubmit().then(() => {
          if (Object.keys(errors).length === 0) {
              const url_delete = JSON_SERVER_URL + '/' + values.id;

              const requestOptions = {
                  method: 'DELETE',
                  headers: { 'Content-Type': 'application/json' },
              };

              fetch(url_delete, requestOptions)
                  .then(response => {
                      if (response.ok) {
                          mensajeFunction("Empleado eliminado")
                          return response.json();
                      } else {
                        mensajeFunction("Error al eliminar empleado")
                      }
                  })
                  .catch(error => console.error('Error:', error));
          }else{mensajeFunction("Campos vacíos")}
      });
  };

  return (
    <div className='EmpleadoStyle_fondo'>
      <BarraNavegacion></BarraNavegacion>
      <div className='EmpleadoStyle_contenedor'>        
        <label className='EmpleadoStyle_label'> Eliminar por id: </label>
        <input className='EmpleadoStyle_input' name='id' type='text' onChange={handleChange} value={values.id} ></input>
        <br></br>
        <button className='EmpleadoStyle_button' onClick={buttonClick}>Eliminar</button>
        <p className='EmpleadoStyle_mensaje'>{mensaje}</p>
      </div>
    </div>
  )
}

export default BajaEmpleado