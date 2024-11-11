package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto.TrabajadorDTO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Direccion;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Empresa;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Trabajador;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.repository.EmpresaRepository;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.repository.TrabajadorRepository;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.service.impl.TrabajadorServiceImpl;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.util.UtilDTO;

class TrabajadorServiceTest {

    @Mock
    private TrabajadorRepository trabajadorRepository;  // Mockeamos el repositorio
    @Mock
    private EmpresaRepository empresaRepository;  // Mockeamos el repositorio

    @InjectMocks
    private TrabajadorServiceImpl trabajadorService;  // Instanciamos el servicio con el repositorio mockeado

    private Trabajador trabajador;
    private TrabajadorDTO trabajadorDTO;
    private Empresa empresa;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
        // Se ejecuta antes de cada test
        trabajador = new Trabajador();
        trabajador.setId(1L);
        trabajador.setNombre("Javier");
        trabajador.setApellidoPaterno("Gonzalez");
        trabajador.setApellidoMaterno("Paz");
        trabajador.setNumeroRut(26541661L);
        trabajador.setDigitoVerificador("6");
        trabajador.setFechaInsercion(LocalDate.now());
        Direccion direccion = new Direccion();
        direccion.setCalleAvenida("Santiago Concha");
        direccion.setNumero("1540");
        direccion.setCiudad("Santiago");
        trabajador.setDireccion(direccion);
        empresa = new Empresa();
        empresa.setId("1731206850559-k7Pv6ePPn7v6I6rorvrs6e6");
        trabajador.setEmpresa(empresa);
        
        trabajadorDTO = UtilDTO.convertirATrabajadorDTO(trabajador);
    }

    @Test
    void testObtenerTrabajadorPorIdCuandoExiste() {
        // Dado que el repositorio devuelve un Optional con el trabajador mockeado
        when(trabajadorRepository.findById(1L)).thenReturn(Optional.of(trabajador));

        // Cuando se llama al servicio para obtener el trabajador
        TrabajadorDTO resultado = trabajadorService.obtenerTrabajadorPorId(1L);

        // Entonces, verificamos que el trabajador obtenido sea el correcto
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Javier", resultado.getNombre());
        assertEquals("Gonzalez", resultado.getApellidoPaterno());
        assertEquals("Paz", resultado.getApellidoMaterno());

        // Verificamos que el repositorio fue llamado una vez con el ID 1
        verify(trabajadorRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerTrabajadorPorIdCuandoNoExiste() {
        // Dado que el repositorio devuelve un Optional vacío
        when(trabajadorRepository.findById(1L)).thenReturn(Optional.empty());

        // Cuando se llama al servicio para obtener el trabajador
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            trabajadorService.obtenerTrabajadorPorId(1L);
        });

        // Entonces, verificamos que la excepción es la esperada
        assertEquals("Trabajador no encontrado", exception.getMessage());

        // Verificamos que el repositorio fue llamado una vez con el ID 1
        verify(trabajadorRepository, times(1)).findById(1L);
    }

    @Test
    void testGuardarTrabajador() {
        // Dado que el repositorio guarda el trabajador correctamente
        when(trabajadorRepository.save(any(Trabajador.class))).thenReturn(trabajador);
        // Dado que el repositorio busca si la empresa asociada al trabajador
        when(empresaRepository.findById(trabajador.getEmpresa().getId())).thenReturn(Optional.of(empresa));

        // Cuando se llama al servicio para guardar el trabajador
        TrabajadorDTO resultado = trabajadorService.guardarTrabajador(trabajadorDTO);

        // Entonces, verificamos que el trabajador guardado es el mismo
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Javier", resultado.getNombre());
        assertEquals("Gonzalez", resultado.getApellidoPaterno());
        assertEquals("Paz", resultado.getApellidoMaterno());

        // Verificamos que el repositorio fue llamado una vez con el trabajador
        verify(trabajadorRepository, times(1)).save(any(Trabajador.class));
    }
}
