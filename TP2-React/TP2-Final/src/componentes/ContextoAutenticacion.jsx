import React, { createContext, useContext, useState } from 'react';
const AutenticacionContext = createContext();

export const ProveedorAutenticacion = ({ children }) => {
    let [isAuthenticated, setIsAuthenticated] = useState(false);
    let [token, setToken] = useState(null);

    const login = (token) => {
        localStorage.setItem("isAuthenticated", 'true');
        localStorage.setItem("token", token);
        setIsAuthenticated(true);
        setToken(token);
    };

    const logout = () => {
        localStorage.setItem("isAuthenticated", 'false');
        localStorage.removeItem("token");
        setIsAuthenticated(false);
        setToken(null);
    };

    return (
        <AutenticacionContext.Provider value={{isAuthenticated, token, login, logout }}>
            {children}
        </AutenticacionContext.Provider>
    );
};

export const useAutenticacion = () => useContext(AutenticacionContext);