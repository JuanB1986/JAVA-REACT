package validations;

import entities.JornadaRequest;
import repositories.ConceptoLaboralRepository;
import repositories.EmpleadoRepository;
import userExceptions.userHttp400Status;
import userExceptions.userHttp404Status;

public class ValidaDatosJornada {

	public static void validar(JornadaRequest jornadaRequest, EmpleadoRepository empleadoRepository, ConceptoLaboralRepository conceptoLaboralRepository) {

		/**
		 * Valida que el campo idEmpleado/idConcepto/fecha no esté vacío.
		 */
		if (jornadaRequest.getIdEmpleado()==null) {
			throw new userHttp400Status("El campo 'idEmpleado' es obligatorio");
		}
		if (jornadaRequest.getIdConcepto()==null) {
			throw new userHttp400Status("El campo 'idConcepto' es obligatorio");
		}
		if (jornadaRequest.getFecha()==null) {
			throw new userHttp400Status("El campo 'fecha' es obligatorio");
		}


		/**
		 * Valida que el campo 'hsTrabajas' no sea cero en turnos Extra y Normal.
		 */
		if(jornadaRequest.getIdConcepto() != 3 && jornadaRequest.getHorasTrabajadas()==0)
		{
			throw new userHttp400Status("El campo 'hsTrabajadas' es obligatorio para el concepto ingresado");
		}


		/**
		 * Valida que el campo 'hsTrabajas' sea cero en Dia Libre.
		 */
		if(jornadaRequest.getIdConcepto() == 3 && jornadaRequest.getHorasTrabajadas() !=0)
		{
			throw new userHttp400Status("El concepto ingresado no requiere el ingreso de 'hsTrabajadas'.");
		}


		/**
		 * Valida que el el empleado exista.
		 */
		if (!empleadoRepository.existsById(jornadaRequest.getIdEmpleado())) {
			throw new userHttp404Status("El existe el empleado ingresado.");
		}


		/**
		 * Valida que exista el concepto ingresado.
		 */
		if (!conceptoLaboralRepository.existsById(jornadaRequest.getIdConcepto())) {
			throw new userHttp404Status("El concepto ingresado no existe.");
		}
	}
}
