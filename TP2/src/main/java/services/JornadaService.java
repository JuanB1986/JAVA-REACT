package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.validator.routines.IntegerValidator;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import entities.ConceptoLaboral;
import entities.Empleado;
import entities.Jornada;
import entities.JornadaRequest;
import entitiesDTO.JornadaDTO;
import repositories.ConceptoLaboralRepository;
import repositories.EmpleadoRepository;
import repositories.JornadaRepository;
import userExceptions.userHttp400Status;
import userExceptions.userHttp404Status;
import validations.ValidaDatosJornada;

@Service
public class JornadaService {

	@Autowired
	private JornadaRepository jornadaRepository;
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private ConceptoLaboralRepository conceptoLaboralRepository;

	//GET
	public List<Jornada> findAllJornadas(Integer nroDocumento, String fechaDesde, String fechaHasta) {

		/**
		 * Campo fechaDesde con formato erroeno.
		 */
		if (fechaDesde!=null) {
			try {
				LocalDate.parse(fechaDesde, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}catch (DateTimeParseException e) {throw new userHttp400Status("El campo 'fechaDesde' debe respetar el formato 'yyyy-mm-dd'");}
		}


		/**
		 * Campo fechaHasta con formato erroeno.
		 */
		if (fechaHasta!=null) {
			try {
				LocalDate.parse(fechaHasta, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}catch (DateTimeParseException e) {throw new userHttp400Status("El campo 'fechaHasta debe respetar el formato 'yyyy-mm-dd'");}
		}


		/**
		 * Valida el número de documento NO ANDA CUANDO INGRESO STRING.
		 */
		try {
			if(nroDocumento != null && !IntegerValidator.getInstance().isInRange(nroDocumento, 1000000, 99999999)){
				throw new userHttp404Status("El campo 'nroDocumento' es erroneo.");
			}
		}catch(MethodArgumentTypeMismatchException e) {throw new userHttp404Status("El campo 'nroDocumento' es erroneo.");}


        /**
         * Filtra por nroDocumento.
         */
		if (fechaDesde == null && fechaHasta == null && nroDocumento != null) {
			return jornadaRepository.findAll().stream().filter(m-> m.getNroDocumento().equals(nroDocumento)).toList();
		}

        /**
         * Filtra por fechas, ignora nro de documento.
         */
		if (fechaDesde != null && fechaHasta != null && nroDocumento == null) {

			LocalDate desde = LocalDate.parse(fechaDesde, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate hasta = LocalDate.parse(fechaHasta, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			return jornadaRepository.findAll().stream().filter(m-> {
				LocalDate dateTime = LocalDate.parse(m.getFecha(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				return (dateTime.isAfter(desde)&& dateTime.isBefore(hasta) || dateTime.isEqual(desde) || dateTime.isEqual(hasta));
			}).collect(Collectors.toList());
		}


        /**
         * Filtra por fechas, y nro de documento.
         */
		if (fechaDesde != null && fechaHasta != null && nroDocumento != null) {

			LocalDate desde = LocalDate.parse(fechaDesde, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			LocalDate hasta = LocalDate.parse(fechaHasta, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			return jornadaRepository.findAll().stream().filter(m-> {
				LocalDate dateTime = LocalDate.parse(m.getFecha(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				return ( ((dateTime.isAfter(desde) && dateTime.isBefore(hasta)  || dateTime.isEqual(desde) || dateTime.isEqual(hasta))&& m.getNroDocumento().equals(nroDocumento)) );
			}).collect(Collectors.toList());
		}


        /**
         * Filtra por fecha desde.
         */
		if (fechaDesde != null && fechaHasta == null && nroDocumento == null) {

			LocalDate desde = LocalDate.parse(fechaDesde, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			return jornadaRepository.findAll().stream().filter(m-> {
				LocalDate dateTime = LocalDate.parse(m.getFecha(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				return (dateTime.isAfter(desde)|| dateTime.isEqual(desde));
			}).collect(Collectors.toList());
		}


        /**
         * Filtra por fecha hasta.
         */
		if (fechaDesde == null && fechaHasta != null && nroDocumento == null) {

			LocalDate hasta = LocalDate.parse(fechaHasta, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			return jornadaRepository.findAll().stream().filter(m-> {
				LocalDate dateTime = LocalDate.parse(m.getFecha(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				return (dateTime.isBefore(hasta)|| dateTime.isEqual(hasta));
			}).collect(Collectors.toList());
		}


		return jornadaRepository.findAll();
	}


	//POST
    public JornadaDTO createJornada(JornadaRequest jornadaRequest) {

    	/**
    	 * Llama a validador.
    	 */
    	ValidaDatosJornada.validar(jornadaRequest, empleadoRepository, conceptoLaboralRepository);

        Empleado empleado = empleadoRepository.findById(jornadaRequest.getIdEmpleado())
            .orElseThrow(() -> new userHttp404Status("Empleado no encontrado."));
        ConceptoLaboral conceptoLaboral = conceptoLaboralRepository.findById(jornadaRequest.getIdConcepto())
            .orElseThrow(() -> new userHttp404Status("Concepto Laboral no encontrado."));


        JornadaBusiness.BusinessRules(jornadaRequest, conceptoLaboral, jornadaRepository, empleadoRepository);

        //entidades completamente cargadas
        Hibernate.initialize(empleado);
        Hibernate.initialize(conceptoLaboral);


        /**
         * Guarda en la base de datos.
         */
        int id = (int)jornadaRepository.count()+1;
        jornadaRepository.save(this.jornadaObjectFactory(conceptoLaboral, empleado, jornadaRequest.getHorasTrabajadas(), jornadaRequest.getFecha(),id));

        return new JornadaDTO(empleado, conceptoLaboral,jornadaRequest.getFecha(), jornadaRequest.getHorasTrabajadas());
    }



    /**
     * Object Factory Jornada
     * Armo el objeto de persistencia para ejecutar POST.
     */
    private Jornada jornadaObjectFactory(ConceptoLaboral conceptoLaboral, Empleado empleado, int horasTrabajadas, String fecha, int id) {

    	Jornada jornada = new Jornada();
    	jornada.setId(id);
    	jornada.setNroDocumento(empleado.getNroDocumento());
        jornada.setNombreCompleto(empleado.getNombre()+" "+empleado.getApellido());
        jornada.setFecha(fecha);
        jornada.setConcepto(conceptoLaboral.getNombre());
        jornada.setHorasTrabajadas(horasTrabajadas);

    	return jornada;
    }


}
