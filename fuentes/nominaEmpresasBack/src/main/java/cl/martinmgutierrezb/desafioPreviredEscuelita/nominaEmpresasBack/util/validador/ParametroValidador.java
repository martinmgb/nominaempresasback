package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.util.validador;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.exceptions.ParametroInvalidoException;

/**
 * Clase para validar parametos
 */
public class ParametroValidador {

	private static final int LONGITUD_MAXIMA_ID_EMPRESA = 50;
	private static final int LONGITUD_MINIMA_ID_EMPRESA = 10;

	/**
	 * Método para validar id del trabajador
	 * @param id
	 * @throws ParametroInvalidoException
	 */
	public static void validarIdTrabajador(Long id) throws ParametroInvalidoException{

		if(id!=null && id > 0) {
			throw new ParametroInvalidoException("Id del trabajador debe ser mayor a 0");
		}
		
    }
	
	/**
	 * Método para validar id de empresa
	 * @param id
	 * @throws ParametroInvalidoException
	 */
	public static void validarIdEmpresa(String id) throws ParametroInvalidoException {

		if(id.isEmpty() || (id.length() >= LONGITUD_MINIMA_ID_EMPRESA && id.length() <= LONGITUD_MAXIMA_ID_EMPRESA)) {
			throw new ParametroInvalidoException("Id de la empresa debe tener una longitud entre [10 y 50] caracteres");
		}
    }

}
