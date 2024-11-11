package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.util.validador;

/**
 * Clase para validar rut
 */
public class RutValidador {

    private static final int MULTIPLICADOR_INICIAL = 2;
	private static final int MULTIPLICADOR_TOPE = 7;
	private static final String DIGITO_K = "K";
	private static final int DIVISOR_MODULO_11 = 11;

	/**
	 * Método para validar rut con procedimiento de modulo 11
	 * @param rut
	 * @param digitoVerificador
	 * @return isValido [boolean]
	 * @see Modulo 11
	 */
	public static boolean validarRut(Long rut, String digitoVerificador) {

        // Calcular el dígito verificador con procedimiento de modulo 11
        int dvCalculado = calcularDigitoVerificador(rut.toString());

        /*
         *  Comparar el dígito verificador calculado con el ingresado
         *  Si es 10 es porque debe ser K sino debe ser el dv calculado
         */
        return (dvCalculado == 10 && (digitoVerificador.equals(DIGITO_K))) ||
                (String.valueOf(dvCalculado).equals(digitoVerificador));
    }

	/**
	 * Método para calcular digito verificador de un rut segun el algoritmo de modulo 11
	 * @param rut
	 * @return digito verificador
	 * @see Modulo 11
	 */
    private static int calcularDigitoVerificador(String rut) {
        int suma = 0;
        int multiplicador = MULTIPLICADOR_INICIAL;

        // Recorrer el RUT de derecha a izquierda y calcular la suma ponderada
        for (int i = rut.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(rut.charAt(i)) * multiplicador;
            multiplicador = (multiplicador == MULTIPLICADOR_TOPE) ? MULTIPLICADOR_INICIAL : multiplicador + 1;
        }

        // Calcular el resto de la división entre 11
        int resto = suma % DIVISOR_MODULO_11;
        return DIVISOR_MODULO_11 - resto;
    }

}
