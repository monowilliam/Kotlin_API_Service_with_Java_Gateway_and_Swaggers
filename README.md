# Proyecto API Service with Kotlin
Estudiante: William Aguirre Zapata

En la carpeta `tests-postman` hay un archivo JSON `Proyecto 2 Kotlin API Service.postman_collection.json` donde se debe importar en Postman de la siguiente forma:
-File -> Import...  |ó|  Ctrol + O

### Compilar Concepts API Service
`$ vagrant ssh kotlinConceptsService`

`$ cd kotlin-concepts-service-api` 

`$ make clean`

`$ make compile`

`$ make execute`

### Compilar Users API Service
`$ vagrant ssh kotlinUsersService`

`$ cd kotlin-users-service-api`

`$ make clean`

`$ make compile`

`$ make execute`

### Compilar Java Gateway
`$ vagrant ssh javaGatewayService`

`$ cd java-gateway`

`$ make clean`

`$ make compile`

`$ make execute`

## Generar Swagger
### Swagger de Users API Service

(1) Se genera en: `http://192.168.56.119:8080/v2/api-docs`

(2) Abrimos la página `https://editor.swagger.io/` e importamos el archivo JSON `swagger-users.json` ubicado en la carpeta `swaggers`

### Swagger de Concepts API Service

(1) Se genera en: `http://192.168.56.120:8080/v2/api-docs`

(2) Abrimos la página `https://editor.swagger.io/` e importamos el archivo JSON `swagger-concepts.json` ubicado en la carpeta `swaggers`

## Pruebas

Una vez importado el archivo en Postman, están listas todos los Endpoint, tanto de cada API con IP por separado como la del Java Gateway, para que puedan ser probador por individual
