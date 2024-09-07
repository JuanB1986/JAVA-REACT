package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.Empleado;
import entitiesDTO.EmpleadoDTO;
import repositories.EmpleadoRepository;
import repositories.JornadaRepository;
import userExceptions.userHttp204Status;
import userExceptions.userHttp400Status;
import userExceptions.userHttp404Status;
import validations.HttpVerb;
import validations.ValidaDatosEmpleado;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Autowired
	private JornadaRepository jornadaRepository;
	
	
	public EmpleadoService(EmpleadoRepository empleadoRepository) {
		this.empleadoRepository = empleadoRepository;
	}
	

	//GET
	public List<EmpleadoDTO> getEmpleados(){
		List<Empleado> empleados = empleadoRepository.findAll();
		List<EmpleadoDTO> empleadosDTO = new ArrayList<>();
		empleados.forEach(m-> empleadosDTO.add(new EmpleadoDTO(m)));
		return empleadosDTO;

	}

	//GET Empleado.
	public EmpleadoDTO getEmpleado(int id){

		if (empleadoRepository.existsById(id)) {
			return new EmpleadoDTO(empleadoRepository.getReferenceById(id));
		}
		else
		{
			throw new userHttp400Status("No se encontró el empleado con Id: " + id);
		}
	}

	//PUT Empleado
	public void putEmpleado(Empleado empleado, int id){

		if (empleadoRepository.existsById(id)) {

			ValidaDatosEmpleado.validaEmpleado(empleadoRepository, empleado, HttpVerb.PUT, id);

			Empleado empleadoBuscado = empleadoRepository.getReferenceById(id);

			Empleado empleadoUpdate = empleadoRepository.getReferenceById(id);
			empleadoUpdate.setId(empleadoBuscado.getId());
			empleadoUpdate.setNombre(empleado.getNombre());
			empleadoUpdate.setApellido(empleado.getApellido());
			empleadoUpdate.setEmail(empleado.getEmail());
			empleadoUpdate.setFechaIngreso(empleado.getFechaIngreso());
			empleadoUpdate.setFechaNacimiento(empleado.getFechaNacimiento());
			empleadoUpdate.setNroDocumento(empleado.getNroDocumento());

			empleadoRepository.save(empleadoUpdate);
		}  
		else
		{
			throw new userHttp400Status("Empleado no encontrado.");
		}
	}

	//POST Empleado
	public void saveEmpleado(Empleado empleado) {
		ValidaDatosEmpleado.validaEmpleado(empleadoRepository,empleado, HttpVerb.POST, 0);
		empleado.setId((int)empleadoRepository.count()+1);
		empleadoRepository.save(empleado);
	}


	//DELETE
	public void deleteEmpleado(int id) {

		if (empleadoRepository.existsById(id)) {

			Empleado empleado = empleadoRepository.getReferenceById(id);

			if(jornadaRepository.existsBynroDocumento(empleado.getNroDocumento())) {
				throw new userHttp404Status("No es posible eliminar un empleado con jornadas asociadas.");
			}
			else
			{
				this.empleadoRepository.deleteById(id);
				throw new userHttp204Status();
			}
		}
		else
		{
			throw new userHttp404Status("No se encontro el empleado con id "+ id);
		}
	}

}
