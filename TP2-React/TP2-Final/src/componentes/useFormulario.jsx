import { useState } from 'react';

const useFormulario = (valoresIniciales, validar) => {
    const [valores, setValores] = useState(valoresIniciales);
    const [errores, setErrores] = useState({});
    const [enviando, setEnviando] = useState(false);

    const manejarCambio = (evento) => {
        const { name, value } = evento.target;
        setValores({
            ...valores,
            [name]: value
        });
    };

    const manejarEnvio = (evento) => {
        evento.preventDefault();
        setErrores(validar(valores));
        setEnviando(true);
    };

    return {
        valores,
        errores,
        manejarCambio,
        manejarEnvio,
        enviando
    };
};

export default useFormulario;