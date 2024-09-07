package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entities.Empleado;
import entitiesDTO.EmpleadoDTO;
import services.EmpleadoService;
import userExceptions.userHttp204Status;
import userExceptions.userHttp400Status;
import userExceptions.userHttp404Status;
import userExceptions.userHttp409Status;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@GetMapping()
	public List<EmpleadoDTO> getEmpleados(){
		return this.empleadoService.getEmpleados();
	}

	@GetMapping("/{id}")
	public EmpleadoDTO getEmpleado(@PathVariable int id) {
		return empleadoService.getEmpleado(id);
	}


	@PostMapping()
	public EmpleadoDTO setEmpleado(@RequestBody Empleado empleado) {
		this.empleadoService.saveEmpleado(empleado);
		return new EmpleadoDTO(empleado);
	}

	@PutMapping("/{id}")
	public EmpleadoDTO putEmpleado(@RequestBody Empleado empleado, @PathVariable int id) {
		empleado.setId(id);
		empleadoService.putEmpleado(empleado, id);
		return new EmpleadoDTO(empleado);
	}

	@DeleteMapping("/{id}")
	public void deleteEmpleado(@PathVariable int id) {
		empleadoService.deleteEmpleado(id);
	}

	@ExceptionHandler(userHttp409Status.class)
	public ResponseEntity<String> handleInvalidAgeException(userHttp409Status e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(userHttp400Status.class)
	public ResponseEntity<String> handleBadRequestException(userHttp400Status e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(userHttp404Status.class)
	public ResponseEntity<String> handleNotFoundException(userHttp404Status e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(userHttp204Status.class)
	public ResponseEntity<String> handleNoContent(userHttp204Status e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
	}
}
