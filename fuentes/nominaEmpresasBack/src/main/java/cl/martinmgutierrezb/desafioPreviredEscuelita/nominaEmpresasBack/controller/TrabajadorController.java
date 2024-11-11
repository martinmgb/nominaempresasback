package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.controller.response.RespuestaApi;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.exceptions.ParametroInvalidoException;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.exceptions.RutInvalidoException;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto.TrabajadorDTO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.service.TrabajadorService;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.util.validador.ParametroValidador;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Clase Controller para la expocisión de la api trabajadores
 * Prefijo: /api/trabajadores
 * Metodos: consultarTrabajadores, consultarTrabajadoresPorEmpresa, consultarTrabajador, guardarTrabajador, eliminarTrabajador
 * Metodos para consulta y manipulacion de datos de trabajadores
 */
@RestController
@RequestMapping("/api/trabajadores")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "Trabajador", description = "API para manejo de datos de trabajadores")
public class TrabajadorController {
	
	public static final Logger LOG_SERVICE = LoggerFactory.getLogger("restservice");

    @Autowired
    private final TrabajadorService trabajadorService;
    
    
    /**
     * Inyeccion de dependecia del service para operar metodos de negocio
     * @param TrabajadorService
     */
    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }
    
    
    /**
     * Metodo para consultar todas los trabajadores
     * @return ResponseEntity<RespuestaApi<List<TrabajadorDTO>>>
     * Retorna respuesta con las trabajadores encontrados
     */
    @Operation(summary = "Obtiene las trabajadores registrados", description = "Este endpoint devuelve un listado de los trabajadores registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trabajadores encontrados", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    @GetMapping(value = "/consultarTrabajadores", produces = "application/json")
    public ResponseEntity<RespuestaApi<List<TrabajadorDTO>>> obtenerTodos() {
        List<TrabajadorDTO> trabajadores = trabajadorService.consultarTodos();
        RespuestaApi<List<TrabajadorDTO>> respuesta = new RespuestaApi<List<TrabajadorDTO>>(
                true,
                "Trabajadores encontrados",
                HttpStatus.OK.value(),
                trabajadores
            );
        return ResponseEntity.ok(respuesta); 
    }
    
    /**
     * Metodo para consultar trabajador por su id
     * @param id
     * @return ResponseEntity<RespuestaApi<TrabajadorDTO>>
     * Retorna respuesta con la trabajador encontrado
     * @throws ParametroInvalidoException
     */
    @Operation(summary = "Obtiene trabajador por id", description = "Este endpoint devuelve el trabajador que encuentra por el id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trabajador encontrado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Trabajador no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    @GetMapping(value = "/consultarTrabajador/{id}", produces = "application/json")
    public ResponseEntity<RespuestaApi<TrabajadorDTO>> obtenerTrabajadorPorId(@PathVariable Long id) {
    	/*
    	 * Validamos el id del trabajador
    	 */
    	ParametroValidador.validarIdTrabajador(id);
    	/*
    	 * Buscamos al trabajador y devolvemos respuesta
    	 */
        TrabajadorDTO trabajador = trabajadorService.obtenerTrabajadorPorId(id);
        RespuestaApi<TrabajadorDTO> respuesta = new RespuestaApi<TrabajadorDTO>(
                true,
                "Trabajador encontrado",
                HttpStatus.OK.value(),
                trabajador
            );
        return ResponseEntity.ok(respuesta); 
    }
    
    /**
     * Metodo para consultar trabajadores de una empresa
     * @param idEmpresa
     * @return ResponseEntity<RespuestaApi<List<TrabajadorDTO>>>
     * Retorna respuesta con la trabajador encontrado
     */
    @Operation(summary = "Obtiene trabajadores por empresa", description = "Este endpoint devuelve el trabajador que encuentra por el id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trabajadores encontrados", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    @GetMapping(value = "/consultarTrabajadoresPorEmpresa/{idEmpresa}", produces = "application/json")
    public ResponseEntity<RespuestaApi<List<TrabajadorDTO>>> obtenerTrabajadoresPorEmpresa(@PathVariable String idEmpresa) {
    	/*
    	 * Validamos el id de la empresa
    	 */
    	ParametroValidador.validarIdEmpresa(idEmpresa);
    	/*
    	 * Obtenemos los trabajadores del la emprersa y devolvemos respuesta
    	 */
    	List<TrabajadorDTO> trabajadores = trabajadorService.obtenerTrabajadorPorEmpresa(idEmpresa);
        RespuestaApi<List<TrabajadorDTO>> respuesta = new RespuestaApi<List<TrabajadorDTO>>(
                true,
                "Trabajadores encontrados",
                HttpStatus.OK.value(),
                trabajadores
            );
        return ResponseEntity.ok(respuesta); 
    }
    
    /**
     * Metodo para guardar un trabajador
     * @param TrabajadorDTO en el cuerpo de la solicitud
     * @return ResponseEntity<RespuestaApi<TrabajadorDTO>>
     * Retorna respuesta con el trabajador guardado y estatus CREATED
     * @throws RutInvalidoException si el rut es inválido
     */
    @Operation(summary = "Guarda una trabajador", description = "Este endpoint persiste la trabajador que recibe por parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trabajador guardado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    @PostMapping(value = "/guardarTrabajador", produces = "application/json")
    public ResponseEntity<RespuestaApi<TrabajadorDTO>> guardarTrabajador(@Valid @RequestBody TrabajadorDTO trabajador) {
        TrabajadorDTO trabajadorGuardada = trabajadorService.guardarTrabajador(trabajador);
        RespuestaApi<TrabajadorDTO> respuesta = new RespuestaApi<TrabajadorDTO>(
                true,
                "Trabajador guardado",
                HttpStatus.CREATED.value(),
                trabajadorGuardada
            );
        return ResponseEntity.ok(respuesta); 
    }
    
    
    /**
     * Metodo para modificar un trabajador.
     * Los campos que puede modificar son: nombre, apellidos, direccion y empresa a la que trabaja
     * @param TrabajadorDTO en el cuerpo de la solicitud
     * @return ResponseEntity<RespuestaApi<TrabajadorDTO>>
     * Retorna respuesta con el trabajador guardado y estatus OK
     * @throws EntityNotFoundException si el id del trabajador no existe
     */
    @Operation(summary = "Modifica datos de una trabajador", description = "Este endpoint modifica el trabajador que recibe por parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trabajador modificado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Trabajador no encontrado"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    @PutMapping(value = "/modificarTrabajador", produces = "application/json")
    public ResponseEntity<RespuestaApi<TrabajadorDTO>> modificarTrabajador(@Valid @RequestBody TrabajadorDTO trabajador) {
        TrabajadorDTO trabajadorModificado = trabajadorService.modificarTrabajador(trabajador);
        RespuestaApi<TrabajadorDTO> respuesta = new RespuestaApi<TrabajadorDTO>(
                true,
                "Trabajador modificada",
                HttpStatus.OK.value(),
                trabajadorModificado
            );
        return ResponseEntity.ok(respuesta); 
    }
    
    /**
     * Metodo para eliminar un trabajador.
     * @param idTrabajador como variable del path
     * @return ResponseEntity<RespuestaApi<TrabajadorDTO>>
     * Retorna estatus OK
     * @throws EntityNotFoundException si el id del trabajador no existe
     */
    @Operation(summary = "Elimina un trabajador", description = "Este endpoint elimina el trabajador por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trabajador eliminado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Trabajador no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    @DeleteMapping(value = "/eliminarTrabajador/{id}", produces = "application/json")
    public ResponseEntity<RespuestaApi<TrabajadorDTO>> eliminar(@PathVariable Long id) {
    	/*
    	 * Validamos el id del trabajador
    	 */
    	ParametroValidador.validarIdTrabajador(id);
    	/*
    	 * Eliminamos trabajador y enviamos respuesta
    	 */
        trabajadorService.eliminarTrabajador(id);
        RespuestaApi<TrabajadorDTO> respuesta = new RespuestaApi<TrabajadorDTO>(
                true,
                "Trabajador eliminado",
                HttpStatus.OK.value(),
                null
            );
        return ResponseEntity.ok(respuesta); 
    }
}