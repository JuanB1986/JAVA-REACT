const nullInputValidation = (values) => {
    const errors = {};

    if (!values.input || values.input.trim() === "") {
        errors.input = 'El campo es requerido';
    }

    if (!values.nombre || values.nombre.trim() === "") {
        errors.nombre = 'El campo es requerido';
    }

    if (!values.id || values.id.trim() === "") {
        errors.id = 'El campo es requerido';
        
    }

    if (!values.puesto || values.puesto.trim() === "") {
        errors.puesto = 'El campo es requerido';
    }

    return errors;
};

export default nullInputValidation;