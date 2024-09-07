package tests;


import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import entities.Empleado;
import repositories.EmpleadoRepository;
import services.EmpleadoService;


@ExtendWith(MockitoExtension.class)
public class tests {

	@Mock
    private EmpleadoRepository empleadoRepository;
	
	@InjectMocks
    private EmpleadoService empleadoService;

    @BeforeEach
    public void setup() {
        empleadoRepository = mock(EmpleadoRepository.class);
        empleadoService = new EmpleadoService(empleadoRepository);
    }

    @Test
    public void repositoryTest() {
        Empleado empleado = new Empleado();
        empleado.setId(1);
        empleado.setNombre("Juan");
        empleado.setApellido("Banquero");
        empleado.setEmail("juan@banquero.com");
        empleado.setFechaNacimiento("1986-08-19");
        empleado.setFechaIngreso("2000-05-09");
        empleado.setNroDocumento(36852741);

        Mockito.when(empleadoRepository.save(empleado)).thenReturn(empleado);
        
        final Empleado resultado = empleadoRepository.save(empleado);
        
        Assertions.assertEquals(resultado.getNroDocumento(), empleado.getNroDocumento());
        
    }
}
