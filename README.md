# 🚀 API REST con Apache Camel y OpenAPI

Este proyecto implementa una API REST utilizando **Spring Boot**, **Apache Camel** y componente **platform-http** para exponer un servicio REST simple, con documentación generada automáticamente mediante **OpenAPI** y **Springdoc**.

## 📌 Descripción

La API expone un endpoint REST `/api/hello` que responde a solicitudes `GET`, devolviendo un mensaje en `application/json`. La documentación se genera de forma automática y está disponible en:

- **Swagger UI**: [`http://localhost:8080/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)
- **Especificación OpenAPI**: [`http://localhost:8080/api-docs`](http://localhost:8080/api-docs)

## 🛠️ Tecnologías

- **Java 11+**
- **Spring Boot 3.4.3**
- **Apache Camel**
- **OpenAPI con Springdoc**
- **camel-openapi-java**

## 📦 Instalación

### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/mcdilan/demo-apache-camel.git
cd demo-apache-camel
```

### 2️⃣ Construir el proyecto

```bash
mvn clean install
```

### 3️⃣ Ejecutar la aplicación

```bash
mvn spring-boot:run
```

## 🔥 Endpoints

| Método | URL          | Descripción                                 |
| ------ | ------------ | ------------------------------------------- |
| `GET`  | `/api/hello` | Retorna un mensaje de saludo en texto plano |

## 📖 Documentación API

Esta API utiliza **Springdoc OpenAPI** y **camel-openapi-java** para generar documentación dinámica.

📌 **Acceder a la documentación:**

- Swagger UI: [`http://localhost:8080/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)
- Especificación OpenAPI (JSON): [`http://localhost:8080/api-docs`](http://localhost:8080/api-docs)

## 🏗️ Arquitectura

📌 **Configuración de Apache Camel:**

- Se utiliza `platform-http` como componente REST.
- Se define el contexto base `/api`.
- Se habilita CORS y se configura el modo JSON para las respuestas.

📌 **Definición de rutas:**

- `` → Redirige a `direct:helloRoute`, que retorna un mensaje fijo.

📌 **Documentación OpenAPI:**

- Definida en `CamelRoute.java` con propiedades como título, versión, contacto y licencia.

## 📧 Contacto

**Autor:** dapavez\
📩 Email: [dapavez@sernapesca.cl](mailto\:dapavez@sernapesca.cl)

