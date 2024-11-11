package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.exceptions;

/**
 * Clase para excepcion de rut inválido por modulo 11
 */
public class RutInvalidoException extends RuntimeException {

	/**
	 * Declaración del serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor con mensaje de error
	 * @param mensaje
	 */ 
    public RutInvalidoException(String mensaje) {
        super(mensaje);  
    }

    /**
	 * Constructor con mensaje de error y causa (Throwable)
	 * @param mensaje y causa
	 */
    public RutInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa); 
    }
    
}
