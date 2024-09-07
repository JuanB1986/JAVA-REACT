package validations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;

import entities.Empleado;
import repositories.EmpleadoRepository;
import userExceptions.userHttp400Status;
import userExceptions.userHttp409Status;

public class ValidaDatosEmpleado {

	public static void validaEmpleado(EmpleadoRepository empleadoRepository ,Empleado empleado, HttpVerb httpVerb, int id) {

		/**
		 * Valida que el campo nombre no esté vacío.
		 */
		if (empleado.getNombre()==null || empleado.getNombre()=="") {
			throw new userHttp400Status("El campo 'nombre' es obligatorio");
		}

		/**
		 * Valida que el campo apellido no esté vacío.
		 */
		if (empleado.getApellido()=="" || empleado.getApellido()==null) {
			throw new userHttp400Status("El campo 'apellido' es obligatorio");
		}


		/**
		 * Valida que el campo 'fechaIngreso' no esté vacío.
		 */
		if (empleado.getFechaIngreso()=="" || empleado.getFechaIngreso()==null) {
			throw new userHttp400Status("El campo 'fechaIngreso' es obligatorio");
		}


		/**
		 * Valida que el campo 'fechaNacimiento' no esté vacío.
		 */
		if (empleado.getFechaNacimiento()=="" || empleado.getFechaNacimiento()==null) {
			throw new userHttp400Status("El campo 'fechaNacimiento' es obligatorio");
		}


		/**
		 * Valida el formato del email
		 */
		if (!EmailValidator.getInstance().isValid(empleado.getEmail())) {
			throw new userHttp409Status("El campo 'email' es incorrecto.");
		}

		/**
		 * Valida el número de documento
		 */
		if (!IntegerValidator.getInstance().isInRange(empleado.getNroDocumento(), 1000000, 99999999)) {
			throw new userHttp409Status("El campo 'nroDocumento' es erroneo.");
		}


		/**
		 * Chequea el formato de fecha de nacimiento
		 */
		try {
			LocalDate.parse(empleado.getFechaNacimiento(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}catch(DateTimeParseException e) {
			throw new userHttp409Status("El campo 'fechaNacimiento' tiene un formato erroneo.");
		}


		/**
		 * Chequea el formato de fecha de ingreso
		 */
		try {
			LocalDate.parse(empleado.getFechaIngreso(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}catch(DateTimeParseException e) {
			throw new userHttp409Status("El campo 'fechaIngreso' tiene un formato erroneo.");
		}



		/**
		 * Valida edad del empleado
		 */
		int edad = LocalDate.now().getYear() -  Integer.parseInt(empleado.getFechaNacimiento().substring(0, 4));
		if (edad < 18 ) {
			throw new userHttp400Status("La edad del empleado no puede ser menor a 18 años.");
		}


		/**
		 * Valida documento repetido para METODO PUT
		 */
		if(httpVerb == HttpVerb.PUT) {

		    List<Empleado> listaFiltradaPorNroDoc = empleadoRepository.findAll().stream()
		            .filter(f -> f.getNroDocumento() == empleado.getNroDocumento() && f.getId() != id)
		            .toList();

			if (!listaFiltradaPorNroDoc.isEmpty()) {
				throw new userHttp409Status("Ya existe un empleado con el documento ingresado..");
			}
		}


		/**
		 * Valida que el documento no esté repetido.
		 */
		if (empleadoRepository.existsBynroDocumento(empleado.getNroDocumento()) && httpVerb == HttpVerb.POST) {
			throw new userHttp409Status("Ya existe un empleado con el documento ingresado.");
		}


		/**
		 * Valida email repetido para METODO PUT
		 */
		if(httpVerb == HttpVerb.PUT) {

		    List<Empleado> listaFiltradaEmail = empleadoRepository.findAll().stream()
		            .filter(f -> f.getEmail().equals(empleado.getEmail()) && f.getId() != id)
		            .toList();

			if (!listaFiltradaEmail.isEmpty()) {
				throw new userHttp409Status("Ya existe un empleado con el email ingresado..");
			}
		}


		/**
		 * Valida email repetido para METODO POST.
		 */
		if (empleadoRepository.existsByemail(empleado.getEmail()) && httpVerb == HttpVerb.POST) {
			throw new userHttp409Status("Ya existe un empleado con el email ingresado.");
		}


		/** YYYY-MM-DD
		 * Valida fecha de ingreso posterior al dia de la fecha
		 */
		int day = Integer.parseInt(empleado.getFechaIngreso().substring(8, 10));
		int month = Integer.parseInt(empleado.getFechaIngreso().substring(5, 7));
		int year = Integer.parseInt(empleado.getFechaIngreso().substring(0, 4));

		if (LocalDate.now().isBefore(LocalDate.of(year, month, day))) {
			throw new userHttp400Status("La fecha de ingreso no puede ser posterior a la del día de la fecha");
		}



		final String caracteres = " abcdefghijklmnñopqrstuvwxyzáéíóúABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ";
		char [] nombreEmpleado = empleado.getNombre().toCharArray();
		char [] apellidoEmpleado = empleado.getApellido().toCharArray();

		/**
		 * Valida el nombre del empleado que no contenga caracteres inválidos.
		 */
		for (char caracter : nombreEmpleado) {
			if (caracteres.indexOf(caracter) ==-1) {
				throw new userHttp400Status("El campo 'nombre' contiene caractéres inválidos.");
			}
		}


		/**
		 * Valida el apellido del empleado que no contenga caracteres inválidos.
		 */
		for (char caracter : apellidoEmpleado) {
			if (caracteres.indexOf(caracter) ==-1) {
				throw new userHttp400Status("El campo 'apellido' contiene caractéres inválidos");
			}
		}



	}




}
