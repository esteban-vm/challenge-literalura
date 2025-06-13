# LiterAlura 📚

Proyecto desarrollado para el desafío de programación de [Alura Latam](https://app.aluracursos.com) **LiterAlura**,
enfocado en poner en práctica los conocimientos adquiridos en desarrollo back-end, consumo de APIs y persistencia
y consulta de datos.

## ✨ Funcionalidades Principales:

_La aplicación muestra un menú por consola con las siguientes opciones:_

1. **Buscar libro por título:** Busca un libro por título en la API de [Gutendex](https://gutendex.com). Si
   se encuentra, lo guarda en la base de datos local junto a la información sobre su autor.
2. **Listar libros registrados:** Muestra la lista de los libros guardados en la base de datos local.
3. **Listar autores registrados:** Muestra la lista de los autores guardados en la base de datos local.
4. **Listar autores vivos en un determinado año:** Pide al usuario un año determinado y muestra una lista de
   autores vivos en ese año.
5. **Listar libros por idioma:** Permite al usuario filtrar los libros guardados en la base de datos de acuerdo
   a su idioma, ya sea español, inglés, francés o portugués.
6. **Listar libros por autor:** Permite al usuario filtrar los libros que ha ido guardando en la base datos local
   de acuerdo a sus autores, siendo éstos previamente mostrados en una lista.
7. **Listar los 10 libros más descargados:** Muestra los 10 libros más populares (con más descargas) guardados en
   la base de datos local.
8. **Ver resumen de un libro:** Muestra el resumen de un libro guardado en la base de datos local, previa búsqueda
   mediante su título.
9. **Mostrar estadísticas:** Muestra la cantidad y el total de descargas de libros guardados localmente, en base
   a su idioma.

## 🛠️ Tecnologías Utilizadas:

- Lenguaje: Java 17.
- Framework: Spring Boot 3.5.0.
- Persistencia de datos: Spring Data JPA.
- Base de datos: PostgreSQL.
- Manipulación de respuestas JSON: Jackson Databind.
- Gestión de dependencias: Apache Maven.
- Traducción de resúmenes de libros: Google Gemini API.

## 📋 Prerrequisitos:

_Antes de ejecutar la aplicación, asegúrate de tener instalados:_

- [JDK (Java Development Kit)](https://www.oracle.com/java/technologies/downloads/) - Versión 17 o superior.
- [PostgreSQL](https://www.postgresql.org/download/) - Una instancia de base de datos activa.

## 🚀 Ejecución del Proyecto:

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/esteban-vm/challenge-literalura.git
   cd literalura
   ```

2. **Configura la base de datos:**
   Abre el archivo de configuración de Spring ubicado en
   `src/main/resources/application.properties` y ajusta los siguientes valores de configuración:
   ```properties
   # Asegúrate de que existan las variables de entorno "DB_HOST", "DB_USER" y "DB_PASSWORD".
   # (O bien, reemplázalas directamente con sus valores correspondientes)
   spring.datasource.url=jdbc:postgresql://${DB_HOST}/literalura
   spring.datasource.username=${DB_USER}
   spring.datasource.password=${DB_PASSWORD}
   ```
   **Nota:** Crea antes la base de datos en PostgreSQL con el nombre "**literalura**", porque es necesaria.

3. **Configura la clave de API de Gemini:**
   Ve a la página web de [Google AI Studio](https://aistudio.google.com/app/apikey?hl=es-419) y sigue los pasos para
   crearla.

   **Nota:** Es posible que tengas que reiniciar el equipo para que la variable de entorno sea reconocida.

4. **Compila y ejecuta** la aplicación desde el IDE de tu preferencia.
   Recomendación: [IntelliJ IDEA](https://www.jetbrains.com/idea/).
