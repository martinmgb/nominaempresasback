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
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.exceptions.RutInvalidoException;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.dto.EmpresaDTO;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.service.EmpresaService;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.util.validador.ParametroValidador;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Clase Controller para la expocisión de la api empresas
 * Prefijo: /api/empresas
 * Metodos: consultarEmpresas, consultarEmpresa, guardarEmpresa, eliminarEmpresa
 * Metodos para consulta y manipulacion de datos de la empresa
 */
@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "Empresa", description = "API para manejo de datos de empresas")
public class EmpresaController {
	
	public static final Logger LOG_SERVICE = LoggerFactory.getLogger("restservice");

    @Autowired
    private final EmpresaService empresaService;
    
    
    /**
     * Inyeccion de dependecia del service para operar metodos de negocio
     * @param EmpresaService
     */
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }
    
    
    /**
     * Metodo para consultar todas las empresas
     * @return ResponseEntity<RespuestaApi<List<EmpresaDTO>>>
     * Retorna respuesta con las empresas encontradas
     */
    @Operation(summary = "Obtiene las empresas registradas", description = "Este endpoint devuelve un listado de las empresas registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresas encontradas", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = EmpresaDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    @GetMapping(value = "/consultarEmpresas", produces = "application/json")
    public ResponseEntity<RespuestaApi<List<EmpresaDTO>>> obtenerTodos() {
        List<EmpresaDTO> empresas = empresaService.consultarTodas();
        RespuestaApi<List<EmpresaDTO>> respuesta = new RespuestaApi<List<EmpresaDTO>>(
                true,
                "Empresas encontradas",
                HttpStatus.OK.value(),
                empresas
            );
        return ResponseEntity.ok(respuesta); 
    }
    
    /**
     * Metodo para consultar empresa por su id
     * @return ResponseEntity<RespuestaApi<EmpresaDTO>>
     * Retorna respuesta con la empresa encontrada
     * @throw ParametroInvalidoException
     */
    @Operation(summary = "Obtiene empresa por id", description = "Este endpoint devuelve la empresa que encuentra por el id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    @GetMapping(value = "/consultarEmpresa/{id}", produces = "application/json")
    public ResponseEntity<RespuestaApi<EmpresaDTO>> obtenerEmpresaPorId(@PathVariable String id) {
    	/*
		 * Validamos que no venga vacio del parametro
		 */
		ParametroValidador.validarIdEmpresa(id);
		/*
		 * Buscamos la empresa y devolvemos la respuesta
		 */
        EmpresaDTO empresa = empresaService.obtenerEmpresaPorId(id);
        RespuestaApi<EmpresaDTO> respuesta = new RespuestaApi<EmpresaDTO>(
                true,
                "Empresa encontrada",
                HttpStatus.OK.value(),
                empresa
            );
        return ResponseEntity.ok(respuesta); 
    }
    
    /**
     * Metodo para guardar una empresa
     * @param EmpresaDTO en el cuerpo de la solicitud
     * @return ResponseEntity<RespuestaApi<EmpresaDTO>>
     * Retorna respuesta con la empresa guardada y estatus CREATED
     * @throws RutInvalidoException si el rut es inválido
     */
    @Operation(summary = "Guarda una empresa", description = "Este endpoint persiste la empresa que recibe por parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa guardada", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    @PostMapping(value = "/guardarEmpresa", produces = "application/json")
    public ResponseEntity<RespuestaApi<EmpresaDTO>> guardarEmpresa(@Valid @RequestBody EmpresaDTO empresa) {
        EmpresaDTO empresaGuardada = empresaService.guardarEmpresa(empresa);
        RespuestaApi<EmpresaDTO> respuesta = new RespuestaApi<EmpresaDTO>(
                true,
                "Empresa guardada",
                HttpStatus.CREATED.value(),
                empresaGuardada
            );
        return ResponseEntity.ok(respuesta); 
    }
    
    
    /**
     * Metodo para modificar una empresa.
     * Los campos que puede modificar son: razón social
     * @param EmpresaDTO en el cuerpo de la solicitud
     * @return ResponseEntity<RespuestaApi<EmpresaDTO>>
     * Retorna respuesta con la empresa guardada y estatus OK
     * @throws EntityNotFoundException si el id de la empresa no existe
     */
    @Operation(summary = "Modifica datos de una empresa", description = "Este endpoint modifica la empresa que recibe por parametro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa modificada", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    @PutMapping(value = "/modificarEmpresa", produces = "application/json")
    public ResponseEntity<RespuestaApi<EmpresaDTO>> modificarEmpresa(@Valid @RequestBody EmpresaDTO empresa) {
        EmpresaDTO empresaModificada = empresaService.modificarEmpresa(empresa);
        RespuestaApi<EmpresaDTO> respuesta = new RespuestaApi<EmpresaDTO>(
                true,
                "Empresa modificada",
                HttpStatus.OK.value(),
                empresaModificada
            );
        return ResponseEntity.ok(respuesta); 
    }
    
    /**
     * Metodo para eliminar una empresa.
     * Se eliminaran en conjunto los trabajadores que tenga la empresa
     * @param idEmpresa como variable del path
     * @return ResponseEntity<RespuestaApi<EmpresaDTO>> 
     * Retorna estatus OK
     * @throws EntityNotFoundException si el id de la empresa no existe
     */
    @Operation(summary = "Elimina una empresa", description = "Este endpoint elimina la empresa por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa eliminada", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    @DeleteMapping(value = "/eliminarEmpresa/{id}", produces = "application/json")
    public ResponseEntity<RespuestaApi<EmpresaDTO>> eliminar(@PathVariable String id) {
    	/*
		 * Validamos que no venga vacio del parametro
		 */
		ParametroValidador.validarIdEmpresa(id);
        empresaService.eliminarEmpresa(id);
        RespuestaApi<EmpresaDTO> respuesta = new RespuestaApi<EmpresaDTO>(
                true,
                "Empresa eliminada",
                HttpStatus.OK.value(),
                null
            );
        return ResponseEntity.ok(respuesta); 
    }
}