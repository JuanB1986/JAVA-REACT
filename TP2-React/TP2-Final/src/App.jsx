import React from 'react';
import { ProveedorAutenticacion } from './componentes/ContextoAutenticacion';
import AppRouter from './routers/AppRouter';

const App = () => {
    return (
        <ProveedorAutenticacion>
            <AppRouter />
        </ProveedorAutenticacion>
    );
};

export default App;