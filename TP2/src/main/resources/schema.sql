CREATE TABLE IF NOT EXISTS concepto_laboral (
    id INT PRIMARY KEY,
    hs_maximo INT,
    hs_minimo INT,
    laborable BOOLEAN,
    nombre VARCHAR(45)
);
CREATE TABLE IF NOT EXISTS empleados(
	id INT PRIMARY KEY,
	nro_Documento INT, 
	nombre VARCHAR(45), 
	apellido VARCHAR(45), 
	email VARCHAR(45), 
	fecha_Nacimiento VARCHAR(45), 
	fecha_Ingreso VARCHAR(45)
);
CREATE TABLE IF NOT EXISTS jornada(
	id INT PRIMARY KEY,
	concepto VARCHAR(45),
	fecha	VARCHAR(45),
	horas_trabajadas INT,
	nombre_completo VARCHAR(45),
	nro_documento INT
);