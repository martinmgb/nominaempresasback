package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto.EmpresaDTO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Empresa;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.repository.EmpresaRepository;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.service.impl.EmpresaServiceImpl;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.util.UtilDTO;

public class EmpresaServiceTest {
    @Mock
    private EmpresaRepository empresaRepository;  // Mockeamos el repositorio

    @InjectMocks
    private EmpresaServiceImpl empresaService;  // Instanciamos el servicio con el repositorio mockeado

    private Empresa empresa;
    private EmpresaDTO empresaDTO;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
        empresa = new Empresa();
        empresa.setId("1731206850559-k7Pv6ePPn7v6I6rorvrs6e6");
        empresa.setRazonSocial("Inversiones MM");
        empresa.setNumeroRut(77667797L);
        empresa.setDigitoVerificador("3");
        
        empresaDTO = UtilDTO.convertirAEmpresaDTO(empresa);
    }

    @Test
    void testObtenerEmpresaPorIdCuandoExiste() {
        // Dado que el repositorio devuelve un Optional con la empresa mockeado
        when(empresaRepository.findById(any(String.class))).thenReturn(Optional.of(empresa));

        // Cuando se llama al servicio para obtener la empresa
        EmpresaDTO resultado = empresaService.obtenerEmpresaPorId("1731206850559-k7Pv6ePPn7v6I6rorvrs6e6");

        // Entonces, verificamos que la empresa obtenido sea el correcto
        assertNotNull(resultado);
        assertEquals("1731206850559-k7Pv6ePPn7v6I6rorvrs6e6", resultado.getId());
        assertEquals("Inversiones MM", resultado.getRazonSocial());
        assertEquals(77667797L, resultado.getRut().getNumero());

        // Verificamos que el repositorio fue llamado una vez con el ID 1
        verify(empresaRepository, times(1)).findById(any(String.class));
    }

    @Test
    void testObtenerEmpresaPorIdCuandoNoExiste() {
        // Dado que el repositorio devuelve un Optional vacío
        when(empresaRepository.findById("1731206850559-k7Pv6ePPn7v6I6rorvrs6e6")).thenReturn(Optional.empty());

        // Cuando se llama al servicio para obtener la empresa
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            empresaService.obtenerEmpresaPorId("1731206850559-k7Pv6ePPn7v6I6rorvrs6e6");
        });

        // Entonces, verificamos que la excepción es la esperada
        assertEquals("Empresa no encontrada", exception.getMessage());

        // Verificamos que el repositorio fue llamado una vez con el ID 1
        verify(empresaRepository, times(1)).findById("1731206850559-k7Pv6ePPn7v6I6rorvrs6e6");
    }

    @Test
    void testGuardarEmpresa() {
        // Dado que el repositorio guarda la empresa correctamente
        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);

        // Cuando se llama al servicio para guardar la empresa
        EmpresaDTO resultado = empresaService.guardarEmpresa(empresaDTO);

        // Entonces, verificamos que la empresa guardado es el mismo
        assertNotNull(resultado);
        assertEquals("1731206850559-k7Pv6ePPn7v6I6rorvrs6e6", resultado.getId());
        assertEquals("Inversiones MM", resultado.getRazonSocial());
        assertEquals(77667797L, resultado.getRut().getNumero());

        // Verificamos que el repositorio fue llamado una vez con la empresa
        verify(empresaRepository, times(1)).save(any(Empresa.class));
    }
}
