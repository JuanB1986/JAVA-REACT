import { useState } from 'react';

export const useAuth = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(() => {
        return localStorage.getItem("isAuthenticated") === 'true';
    });
    const [token, setToken] = useState(() => {
        return localStorage.getItem("token");
    });

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

    return { isAuthenticated, token, login, logout };
};