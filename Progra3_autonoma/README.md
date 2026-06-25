# Progra3 Autonoma

Proyecto Spring Boot básico para gestionar una biblioteca con las siguientes entidades:

- `Author` con relación uno a uno con `Profile`
- `Author` con relación uno a muchos con `Book`
- `Book` con relación muchos a muchos con `Category`

## Estructura implementada

- `pom.xml` con dependencias Spring Boot Web, Thymeleaf, JPA y H2
- `application.properties` para base de datos H2 en memoria y consola H2 habilitada
- Entidades JPA en `src/main/java/com/example/progra3/model`
- Repositorios en `src/main/java/com/example/progra3/repository`
- Controladores CRUD en `src/main/java/com/example/progra3/controller`
- Vistas Thymeleaf en `src/main/resources/templates`

## Cómo ejecutar

1. Instalar Maven si no está disponible.
2. Ejecutar en la raíz del proyecto:
   ```bash
   mvn spring-boot:run
   ```
3. Abrir en el navegador:
   - `http://localhost:8080/`
   - `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:progra3db`)

## Notas

- Se generó un proyecto básico con CRUD para autores, libros y categorías.
- Los cambios se almacenan en memoria en el servidor H2 mientras la aplicación está en ejecución.
- No se pudo verificar compilación con Maven en este entorno porque `mvn` no está disponible.
