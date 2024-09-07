/*	INSERTA CONCEPTOS HORARIOS*/
INSERT INTO concepto_laboral(id, hs_maximo, hs_minimo, laborable, nombre) VALUES (1, 8, 6, TRUE, 'Turno Normal');
INSERT INTO concepto_laboral(id, hs_maximo, hs_minimo, laborable, nombre) VALUES (2, 6, 2, TRUE, 'Turno Extra');
INSERT INTO concepto_laboral(id, hs_maximo, hs_minimo, laborable, nombre) VALUES (3, NULL, NULL, FALSE, 'Día Libre');
/*	INSERTA ALGUNOS EMPLEADOS*/
INSERT INTO empleados(id, nro_Documento, nombre, apellido, email, fecha_Nacimiento, fecha_Ingreso) VALUES (1, 33445566, 'Juan', 'Banquero', 'Juan@Juan.com', '1986-08-19', '2016-05-06');
INSERT INTO empleados(id, nro_Documento, nombre, apellido, email, fecha_Nacimiento, fecha_Ingreso) VALUES (2, 2345678, 'Nikola', 'Tesla', 'Nikola@tesla.com', '1886-08-19', '1900-05-06');
INSERT INTO empleados(id, nro_Documento, nombre, apellido, email, fecha_Nacimiento, fecha_Ingreso) VALUES (3, 8529632, 'Edward', 'Munch', 'eMunch@Arte.com', '1905-08-19', '1932-05-06');
INSERT INTO empleados(id, nro_Documento, nombre, apellido, email, fecha_Nacimiento, fecha_Ingreso) VALUES (4, 9876543, 'Roberto', 'Banquero', 'RBanquero@Banquero.com', '1968-08-19', '1985-05-06');
INSERT INTO empleados(id, nro_Documento, nombre, apellido, email, fecha_Nacimiento, fecha_Ingreso) VALUES (5, 6532897, 'Mirta', 'Banquero', 'MBanquero@Banquero.com', '1952-08-19', '1975-05-06');
INSERT INTO empleados(id, nro_Documento, nombre, apellido, email, fecha_Nacimiento, fecha_Ingreso) VALUES (6, 7418529, 'Steve', 'Jobs', 'Steve@Jobs.com', '1954-08-19', '1979-05-06');

/*INSERTA JORNADAS*/
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (1, 'Turno Normal', '2024-08-01', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (2, 'Turno Normal', '2024-08-02', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (3, 'Turno Normal', '2024-08-03', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (4, 'Turno Normal', '2024-08-04', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (5, 'Turno Normal', '2024-08-05', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (6, 'Turno Normal', '2024-08-08', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (7, 'Turno Normal', '2024-08-09', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (8, 'Turno Normal', '2024-08-10', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (9, 'Turno Normal', '2024-08-11', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (10, 'Turno Normal', '2024-08-12', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (11, 'Turno Normal', '2024-08-15', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (12, 'Turno Normal', '2024-08-16', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (13, 'Turno Normal', '2024-08-17', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (14, 'Turno Normal', '2024-08-18', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (15, 'Turno Normal', '2024-08-19', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (16, 'Turno Normal', '2024-08-22', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (17, 'Turno Normal', '2024-08-23', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (18, 'Turno Normal', '2024-08-24', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (19, 'Turno Normal', '2024-08-25', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (20, 'Turno Normal', '2024-08-26', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (21, 'Turno Normal', '2024-08-29', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (22, 'Turno Normal', '2024-08-30', 9, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (23, 'Turno Normal', '2024-09-01', 7, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (24, 'Turno Normal', '2024-10-01', 11, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (25, 'Turno Normal', '2024-10-02', 11, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (26, 'Turno Normal', '2024-10-03', 11, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (27, 'Turno Normal', '2024-10-04', 11, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (28, 'Turno Normal', '2024-10-05', 10, 'Juan Banquero','33445566');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (29, 'Día Libre', '2024-11-01', 0, 'Juan Banquero','33445566');
/**/
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (30, 'Día Libre', '2024-11-04', 0, 'Roberto Banquero','9876543');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (31, 'Turno Normal', '2024-11-05', 1, 'Roberto Banquero','9876543');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (32, 'Turno Extra', '2024-11-06', 2, 'Roberto Banquero','9876543');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (34, 'Día Libre', '2024-11-07', 0, 'Roberto Banquero','9876543');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (35, 'Día Libre', '2024-11-08', 0, 'Roberto Banquero','9876543');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (36, 'Día Libre', '2024-11-11', 0, 'Roberto Banquero','9876543');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (37, 'Día Libre', '2024-11-12', 0, 'Roberto Banquero','9876543');
/*Jornadas para prueba de horas semanales*/
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (38, 'Turno Normal', '2024-12-02', 12, 'Mirta Banquero','6532897');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (39, 'Turno Normal', '2024-12-03', 12, 'Mirta Banquero','6532897');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (40, 'Turno Normal', '2024-12-04', 12, 'Mirta Banquero','6532897');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (41, 'Turno Normal', '2024-12-05', 12, 'Mirta Banquero','6532897');
/*Jornadas para prueba turnos extra. Regla 6*/
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (42, 'Turno Extra', '2024-12-09', 6, 'Steve Jobs','7418529');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (43, 'Turno Extra', '2024-12-10', 6, 'Steve Jobs','7418529');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (44, 'Turno Extra', '2024-12-11', 6, 'Steve Jobs','7418529');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (45, 'Día Libre', '2024-12-16', 6, 'Steve Jobs','7418529');
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (46, 'Día Libre', '2024-12-17', 6, 'Steve Jobs','7418529');
/*Regla 11*/
INSERT INTO jornada(id,concepto,fecha,horas_trabajadas,nombre_completo,nro_documento) VALUES (47, 'Turno Extra', '2025-01-06',4, 'Edward Munch','8529632');
