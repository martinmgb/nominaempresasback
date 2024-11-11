package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Empresa;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity.Trabajador;

public class TrabajadorRepositoryTest {

    @Mock
    private TrabajadorRepository trabajadorRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    
    /**
     * Test de método implementado con @Query en las interfaces Repository 
     * El método se encarga de buscar los trabajador de una empresa particular
     * a través del id de la empresa
     * 
     * @param idEmpresa: Id de la Empresa
     * @return Lista de trabajadores de la empresa (List<Trabajador>)
     */
    @Test
    void testFindByEmpresaId() {
        // Datos de prueba
        String idEmpresa = "1";
        Trabajador t1 = new Trabajador();
        t1.setNumeroRut(26087016L);
        t1.setDigitoVerificador("5");
        t1.setNombre("Juan");
        t1.setApellidoPaterno("Perez");
        t1.setApellidoMaterno("Gonzalez");
        t1.setEmpresa(new Empresa());
        
        Trabajador t2 = new Trabajador();
        t2.setNumeroRut(26541661L);
        t2.setDigitoVerificador("6");
        t2.setNombre("Maria");
        t2.setApellidoPaterno("Castillo");
        t2.setApellidoMaterno("Ferrer");
        t2.setEmpresa(new Empresa());
        
        List<Trabajador> trabajadores = Arrays.asList(t1, t2);

        // Simulación de la llamada al método
        when(trabajadorRepository.findByEmpresaId(idEmpresa)).thenReturn(trabajadores);

        // Verificación de la interacción y aserciones
        List<Trabajador> resultado = trabajadorRepository.findByEmpresaId(idEmpresa);
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
    }
}

