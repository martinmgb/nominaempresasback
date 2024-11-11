package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.advice;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.controller.response.RespuestaApi;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.exceptions.ParametroInvalidoException;
import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.exceptions.RutInvalidoException;


/**
 *Clase para manejo globales de exceptiones en el proyecto
 *manejando para los casos de las siguientes excepciones una respuesta controlada 
 *al cliente que consume la api.
 *
 * Excepciones controladas:
 * Exception, EntityNotFoundException, MethodArgumentNotValidException
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manejar una excepción genérica (por ejemplo, cualquier RuntimeException)
     * @param Exception
     * @return Respuesta con mensaje de error y status INTERNAL_SERVER_ERROR [ResponseEntity<RespuestaApi<String>>]
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaApi<String>> handleGeneralException(Exception ex) {
        RespuestaApi<String> respuesta = new RespuestaApi<>(
            false,
            "Error inesperado: " + ex.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }

    /**
     * Manejar una excepción de tipo EntityNotFoundException
     * @param EntityNotFoundException
     * @return Respuesta con mensaje de error y status NOT_FOUND [ResponseEntity<RespuestaApi<String>>]
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RespuestaApi<String>> handleUsuarioNotFound(EntityNotFoundException ex) {
        RespuestaApi<String> respuesta = new RespuestaApi<>(
            false,
            "Entidad no encontrada: " + ex.getMessage(),
            HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    /**
     * Manejar una excepción de validación
     * @param MethodArgumentNotValidException
     * @return Respuesta con mensaje de error y status BAD_REQUEST [ResponseEntity<RespuestaApi<String>>]
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RespuestaApi<String>> handleValidationException(MethodArgumentNotValidException ex) {
        String mensajeError = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        RespuestaApi<String> respuesta = new RespuestaApi<>(
            false,
            "Error de validación: " + mensajeError,
            HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }
    
    /**
     * Manejar una excepción de tipo RutInvalidoException
     * @param RutInvalidoException
     * @return Respuesta con mensaje de error y status NOT_FOUND [ResponseEntity<RespuestaApi<String>>]
     */
    @ExceptionHandler(RutInvalidoException.class)
    public ResponseEntity<RespuestaApi<String>> handleUsuarioNotFound(RutInvalidoException ex) {
        RespuestaApi<String> respuesta = new RespuestaApi<>(
            false,
            "Rut Inválido: " + ex.getMessage(),
            HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }
    
    /**
     * Manejar una excepción de tipo ParametroInvalidoException
     * @param ParametroInvalidoException
     * @return Respuesta con mensaje de error y status NOT_FOUND [ResponseEntity<RespuestaApi<String>>]
     */
    @ExceptionHandler(ParametroInvalidoException.class)
    public ResponseEntity<RespuestaApi<String>> handleUsuarioNotFound(ParametroInvalidoException ex) {
        RespuestaApi<String> respuesta = new RespuestaApi<>(
            false,
            "Parámetro Inválido: " + ex.getMessage(),
            HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }
}

