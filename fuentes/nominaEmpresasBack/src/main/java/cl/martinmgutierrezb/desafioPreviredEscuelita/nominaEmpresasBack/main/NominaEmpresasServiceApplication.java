package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.config.NominaEmpresasProperties;

/**
 * Clase principal del servicio
 * @author martinmgutierrezb
 * Se escanean todos los beans para que carguen en el contexto
 * Se configura el corsFilter para definir los origines y metodos permitidos
 */
@SpringBootApplication
@EnableConfigurationProperties(NominaEmpresasProperties.class)
@EnableJpaRepositories(basePackages = "cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.repository")
@EntityScan("cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.model.entity")
@ComponentScan(basePackages = "cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack")
public class NominaEmpresasServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NominaEmpresasServiceApplication.class, args);
	}

    @Bean
    CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}
}
