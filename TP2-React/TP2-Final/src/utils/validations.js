const nullInputValidation = (values) => {
    const errors = {};

    if (values.nombre !=null && values.nombre.trim() === "") {
        errors.nombre = 'El campo nombre es requerido';
    }

    if (values.id != null && values.id.trim() === "") {
        errors.id = 'El campo id es requerido';
    }

    if (values.puesto != null && values.puesto.trim() === "") {
        errors.puesto = 'El campo puesto es requerido';
    }

    return errors;
};

export default nullInputValidation;