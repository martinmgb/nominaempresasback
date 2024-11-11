package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.util;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

/**
 * Clase utilitaria para creación de identificadores
 */

public class UtilIdentificador {
	
	/**
	 * Método para crear identificador de empresa usando el instante de tiempo,
	 * razon social, rut y digito verificador
	 * Se genera un número aleatorio entre 10 y 50 para la longitud del id
	 * Añadimos caracteres aleatorios hasta alcanzar la longitud requerida
	 * @param razonSocial, rut, digitoVerificsdor
	 * @return identificador
	 */
	public static String crearIdentificadorEmpresa(String razonSocial, Long rut, String digitoVerificador) {
		
		//Definimos aleatoriamente un numero entre 10 y 50 para la longitud el id
        int longitud =  new Random().nextInt(41) + 10;
        
        StringBuilder sb = new StringBuilder();
        /*
         * Iniciamos el identificador con los milisegundos del instante de tiempo junto
         * al digito verificador del rut
         */
        sb.append(Instant.now().toEpochMilli()+digitoVerificador);
        //Usamos los caracteres de su razon social y rut  para completar identificador 
      	String caracteres = (razonSocial+rut).replaceAll("\\s+", "");
      	//Usamos SecureRandom para seleccionar los caracteres de forma aleatoria
      	SecureRandom random = new SecureRandom();
      	//Cuando complete la longitud aleatoria definida devolvemos el identificador 
        while (sb.length() < longitud) {
            sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return sb.toString();
	}
}
