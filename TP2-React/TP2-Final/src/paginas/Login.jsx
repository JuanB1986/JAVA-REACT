import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { useAutenticacion } from '../componentes/ContextoAutenticacion';

import '../styles/Login.css'

const Login = () => {
    const { login } = useAutenticacion();
    const navigate = useNavigate();
    const location = useLocation();
    const [usuario, setUsuario] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();

        if (usuario === '' || password === '') {
            setError('Campos vacíos.');
            return;
        }

        if (usuario === 'admin' && password === '123') {
            const fakeToken = '1234567890abcdef'; 
            login(fakeToken);
            
            const from = location.state?.from?.pathname || '/inicio';
            navigate(from, { replace: true });
        } else {
            setError('Usuario o contraseña incorrectos.');
        }
    };

    return (
        
        <div id='Login_contenedor'>       
            <h2 id='Login_titulo'>Login</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label className='Login_label' htmlFor="usuario">Usuario:</label>
                    <br></br>
                    <input
                        type="text"
                        className="Login_input"
                        value={usuario}
                        onChange={(e) => setUsuario(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label className='Login_label' htmlFor="password">Contraseña:</label>
                    <br></br>
                    <input
                        type="password"
                        className="Login_input"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>                
                <button id ='Login_loginButton' type="submit">Iniciar sesión</button>
                {error && <p id='Login_errorLabel'>{error}</p>}
            </form>
        </div>
    );
};

export default Login;