package cl.ejemplo.demo.route;

import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * Clase CamelRoute - Configura y maneja un servicio REST con Apache Camel con component platform-http.
 * Esta clase define una ruta Camel que responde a solicitudes GET en el endpoint "/api/hello".
 *
 * Autor: @dapavez
 * Fecha de Creación: Marzo 2025
 * Versión: 1.0
 * Descripción:
 *  Esta clase utiliza Apache Camel para crear una API REST básica que responde con un mensaje estático en formato texto plano.
 *
 * Historial de Modificaciones:
 *  - [Fecha] - Descripción de cambios o mejoras realizadas
 *  
 */
@Component  // Anotación que marca esta clase como un componente de Spring, habilitando la gestión de dependencias por parte del contenedor de Spring.
public class CamelRoute extends RouteBuilder {

    @Override
    public void configure() {

        // Configuración global del componente REST, incluyendo la definición del servidor HTTP y el contexto base de la API.
        restConfiguration()
            .component("platform-http")  // Especifica que el componente para manejar las solicitudes REST será 'platform-http', el cual usa HTTP como protocolo.
            .contextPath("/api")        // Define el contexto base para todas las rutas REST como '/api'.
	        .apiContextPath("/api-docs") // Ruta donde se expondrá la documentación OpenAPI
	        .apiProperty("api.title", "API de Ejemplo con Camel - platform-http") // Título de la API
	        .apiProperty("api.version", "1.0.0") // Versión de la API
	        .apiProperty("api.description", "API de Ejemplo con Camel") // Descripción de la API
	        .apiProperty("cors", "true") // Habilita CORS para los endpoints
	        .dataFormatProperty("prettyPrint", "true") // Formatea la salida JSON de manera legible
	        .bindingMode(RestBindingMode.json) // Define el modo de enlace JSON para los endpoints
	        .host("localhost") // Especifica el host donde se ejecuta el servicio
	        .port(8080); // Define el puerto donde se expone la API
	        
     // Definición de la ruta REST "/hello" que maneja solicitudes GET.
        rest("/hello")
            .id("hello-route") // Identificador único del endpoint
            .description("Endpoint de ejemplo con Apache Camel") // Descripción del endpoint para OpenAPI
            .produces("text/plain")  // Establece que el tipo de contenido de la respuesta será texto plano ('text/plain').
            .responseMessage()
                .code(200).message("OK - Respuesta exitosa") // Define un mensaje de respuesta en OpenAPI
            .endResponseMessage()
            .get()  // Configura el endpoint para manejar solicitudes HTTP GET.
            .to("direct:helloRoute"); // Redirige la solicitud al endpoint interno "direct:helloRoute". 
        
        // Ruta interna que maneja la lógica de la respuesta para "direct:helloRoute".
        from("direct:miRuta")
	        .doTry()
	            .process(exchange -> {
	                // Configura la respuesta con tipo de contenido JSON
	                exchange.getIn().setHeader("Content-Type", "application/json");
	                // Establece el cuerpo de la respuesta con un mensaje de saludo
	                exchange.getIn().setBody(Map.of("mensaje", "¡Hola Mundo con Spring Boot 3.4.3 y Apache Camel usando Servlet!"));
	            })
	        .doCatch(Exception.class) // Manejo de excepciones
	            .process(exchange -> {
	                // Configura la respuesta en caso de error con tipo de contenido de texto plano
	                exchange.getIn().setHeader("Content-Type", "text/plain");
	                // Mensaje de error genérico
	                exchange.getIn().setBody("Ocurrió un error inesperado.");
	                // Código de respuesta HTTP 500 (Error interno del servidor)
	                exchange.getIn().setHeader("CamelHttpResponseCode", 500);
	            })
	        .end(); // Fin del bloque try-catch
    }
}
