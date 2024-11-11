# Proyecto NominaEmpresa con PostgreSQL

Este es un proyecto de ejemplo utilizando **Spring Boot** y **PostgreSQL** como base de datos. A continuación, se proporciona información sobre cómo configurar el proyecto, importar la base de datos en PostgreSQL y ejecutar la aplicación tanto en una máquina local como utilizando **Docker Compose**.

## Requisitos

Antes de comenzar, asegúrate de tener los siguientes requisitos instalados en tu sistema:

- **Java 8**
- **Maven 3.9.4** para la gestión de dependencias
- **Sprintboot 2.7.18**
- **Docker** y **Docker Compose** (si deseas ejecutar el proyecto en contenedores, ademas la composición te brinda la base de datos y pgadmin4)
- **PostgreSQL** o un contenedor Docker para PostgreSQL

## Configuración del Proyecto

### Clona el repositorio de Git:

```
git clone https://github.com/martinmgb/nominaempresasback.git
```
### Importar la Base de Datos desde el archivo SQL ubicado en el repositorio

Puedes importarlo de la siguiente manera:

Acceder a tu servidor PostgreSQL:

Si tienes PostgreSQL instalado en tu máquina, puedes usar el siguiente comando para acceder al cliente interactivo de PostgreSQL:
```bash
psql -U postgres -h localhost
```

Si tienes un contenedor Docker con PostgreSQL, accede al contenedor:

docker exec -it nombre_del_contenedor psql -U postgres

Lo puedes hacer tambien desde pgadmin (en tu maquina o contenedor)

### Crear la base de datos (si aún no existe):

En el cliente de PostgreSQL, crea una base de datos para tu proyecto:
```bash
CREATE DATABASE empresas_bd;
```
Lo puedes hacer tambien desde pgadmin (en tu maquina o contenedor)

### Importar el archivo SQL:

Una vez que tengas la base de datos creada, puedes importar el archivo empresas_bd.sql ubicado en /rutaproyecto/script/empresas_bd.sql con el siguiente comando:

```
psql -U postgres -d empresas_bd -f /ruta/a/tu/empresas_bd.sql
```
Si usas Docker, el comando sería similar pero desde dentro del contenedor:

docker exec -i nombre_del_contenedor psql -U postgres -d empresas_bd -f /ruta/a/tu/empresas_bd.sql

Esto importará todas las tablas, datos, índices y relaciones definidas en el archivo .sql a tu base de datos PostgreSQL.

Lo puedes hacer tambien desde pgadmin (en tu maquina o contenedor)

##Ejecutar el Proyecto

###Ejecutar en una máquina local
Compilar y ejecutar el proyecto con Maven imdicando las siguientes variables de entorno:

```bash
DB_HOST=localhost DB_PORT=5432 DB_DATABASE=empresas_bd DB_USER=root DB_PASSWORD=root mvn spring-boot:run mvn spring-boot
```

Esto compilará el proyecto y lo ejecutará en el puerto predeterminado (8080).

Acceder a la aplicación:

Una vez que la aplicación esté en ejecución, podrás acceder a ella mediante un navegador o herramientas como Postman:

http://localhost:8080

Dependiendo de la configuración del proyecto, puedes tener diferentes endpoints disponibles para interactuar con la aplicación.

###Ejecutar con docker compose
Compilar y ejecutar el proyecto con Maven:

```bash
mvn clean compile package
```

Luego crear imagen del proyecto

```bash
docker build -t api-nominaempresas .
```
	
Finalmente ejecutar el docker compose:

```bash
docker-compose up
```
	
Esto levantará tres contenedores: postgres, pgadmin y api-nominaempresas

Todos estos comandos deben ejecutarse en la raiz del fuente donde estan ubicados los archivos de docker.
Para ejecutar en un contenedor Docker, asegúrate de tener Docker y Docker Compose instalados y configurados correctamente en tu máquina.
Si no tienes docker-compose instalado, puedes hacerlo siguiendo la guía oficial de Docker Compose.

Acceder a la aplicación:

Una vez que la aplicación esté en ejecución, podrás acceder a ella mediante un navegador o herramientas como Postman:

http://localhost:8080

##Documentacion de API (Swagger)
Puedes consultar la documentacion de la API para consumir sus metodos en la siguiente url:
http://localhost:8080/swagger-ui/index.html

##Tecnologías Usadas

Spring Boot (Framework para aplicaciones Java)
PostgreSQL (Base de datos relacional)
Docker y Docker Compose (Contenerización y orquestación)
Maven (Gestión de dependencias)

#Estructura del Proyecto
```textplain
fuentes/nominaEmpresasBack/
├── src/
│ ├── main/
│ │ ├── java/
│ │ ├── resources/
│ │ │ ├── application.properties
├── target/
│ └── mi-aplicacion.jar
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
script/
├── empresas_bd.sql
```

src/main/java: Contiene el código fuente de la aplicación.
src/main/resources: Contiene los archivos de configuración, como application.properties.
target: Contendrá el archivo JAR generado al compilar el proyecto con Maven.
Dockerfile: Archivo para construir la imagen Docker de la aplicación.
docker-compose.yml: Archivo de configuración para Docker Compose.


##Notas adicionales
Si estás ejecutando el proyecto en tu máquina local y tienes problemas de conexión con PostgreSQL, asegúrate de que el servicio de PostgreSQL esté en ejecución y accesible en el puerto configurado (5432 por defecto).

