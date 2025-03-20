package cl.ejemplo.demo.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Clase CamelRoute - Configura y maneja un servicio REST con Apache Camel.
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
            .contextPath("/api");       // Define el contexto base para todas las rutas REST como '/api'.

        // Definición de la ruta REST "/hello" que maneja solicitudes GET.
        rest("/hello")
            .get()  // Configura el endpoint para manejar solicitudes HTTP GET.
            .produces("text/plain")  // Establece que el tipo de contenido de la respuesta será texto plano ('text/plain').
            .to("direct:helloRoute"); // Redirige la solicitud al endpoint interno "direct:helloRoute".

        // Ruta interna que maneja la lógica de la respuesta para "direct:helloRoute".
        from("direct:helloRoute")
            .setHeader("Content-Type", constant("text/plain"))  // Establece el encabezado 'Content-Type' como 'text/plain'.
            .setBody(constant("Hola desde Apache Camel"));  // Establece el cuerpo de la respuesta como un mensaje fijo.
    }
}
