package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entities.Jornada;
import entities.JornadaRequest;
import entitiesDTO.JornadaDTO;
import services.JornadaService;
import userExceptions.userHttp400Status;
import userExceptions.userHttp404Status;
import userExceptions.userHttp409Status;

@RestController
@RequestMapping("/jornada")
public class JornadaController {

    @Autowired
    private JornadaService jornadaService;

    @PostMapping
    public JornadaDTO createJornada(@RequestBody JornadaRequest jornadaRequest) {
        return jornadaService.createJornada(jornadaRequest);
    }


    //@RequestParam(value = "id", required = false) Integer id,

    @GetMapping
    public List<Jornada> getJornadas(
    		@RequestParam(value = "nroDocumento", required=false) Integer nroDocumento,
    		@RequestParam(value = "fechaDesde", required=false) String fechaDesde,
			@RequestParam(value = "fechaHasta", required=false) String fechaHasta)
    {

        return jornadaService.findAllJornadas(nroDocumento, fechaDesde, fechaHasta);
    }
    

    /*
    @GetMapping("/{id}")
    public Jornada getJornadaById(@PathVariable int id) {
        return jornadaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Jornada updateJornada(@PathVariable int id, @RequestBody Jornada jornada) {
        if (jornadaRepository.existsById(id)) {
            jornada.setIdConcepto(id);
            return jornadaRepository.save(jornada);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteJornada(@PathVariable int id) {
        jornadaRepository.deleteById(id);
    }*/

	@ExceptionHandler(userHttp409Status.class)
	public ResponseEntity<String> handleConflictException(userHttp409Status e) {
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

}