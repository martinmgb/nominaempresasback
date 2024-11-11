package cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack.config;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Clase para configurar swagger y documentar las api, sus endpoints y sus metodos.
 * Implementado con OpenAPI
 *
 */
@Configuration
public class SwaggerConfig {

    
	/**
     * Configura OpenAPI para documentar la API y personalizar detalles globales.
     */
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Nomina Empresas Back API")
                        .version("1.0.0")
                        .description("API para gestionar la nómina de empresas y trabajadores.")
                        .contact(new Contact().name("Martín Manuel Gutiérrez").email("martinmgutierrezb@gmail.com")));
    }
    
    /**
     * Configuracion de donde buscara y hará escaner con las anotaciones de swagger
     */
    @Bean
    GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("nominaEmpresasBack")
                .packagesToScan("cl.martinmgutierrezb.desafioPreviredEscuelita.nominaEmpresasBack")
                .build();
    }
}
