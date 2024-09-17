import BarraNavegacion from '../../componentes/BarraNavegacion';
import JSON_SERVER_URL from '../../json-server-config';
import '../../styles/EmpleadoStyle.css'
import nullInputValidation from '../../utils/validations';
import useForm from '../../hooks/useForm';

const BajaEmpleado = () => {
  
    const initialValues = { input: '' };
    const { values, errors, handleChange, handleSubmit } = useForm(initialValues, nullInputValidation);

    const buttonClick = () => {     
      handleSubmit().then(() => {
          if (Object.keys(errors).length === 0) {
              const url_delete = JSON_SERVER_URL + '/' + values.input;

              const requestOptions = {
                  method: 'DELETE',
                  headers: { 'Content-Type': 'application/json' },
              };

              fetch(url_delete, requestOptions)
                  .then(response => {
                      if (response.ok) {
                          console.log('Recurso eliminado');
                          return response.json();
                      } else {
                          console.error('Error al eliminar el recurso');
                      }
                  })
                  .catch(error => console.error('Error:', error));
          }
      });
  };

  return (
    <div >
      <BarraNavegacion></BarraNavegacion>
      <div className='EmpleadoStyle_contenedor'>        
        <label className='EmpleadoStyle_label'> Eliminar por id: </label>
        <input className='EmpleadoStyle_input' name='input' type='text' onChange={handleChange} value={values.input} ></input>
        <br></br>
        <button className='EmpleadoStyle_button' onClick={buttonClick}>Eliminar</button>
      </div>
    </div>
  )
}

export default BajaEmpleado