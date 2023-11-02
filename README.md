# MatrixCampus_PruebaTecnica

## INTRODUCCIÓN

Como solución a esta prueba técnica he planteado un servicio SpringBoot que proporciona el endpoint "/consultar-precio" definido como una solicitud GET.
Se ha usado una base de datos en memoria de tipo h2 cuya consola está accesible desde http://localhost:8080/h2-console y requiere los siguientes parámetros de conexión:
* Driver class: org.h2.Driver
* JDBC URL: jdbc:h2:mem:comercio
* Usuario: h2
* Contraseña: h2

## SERVICIO

La organización interna del servicio es la siguiente:

<pre>
com
|-- prueba
    |-- controller
        |-- PrecioController.java
    |-- service
        |-- PrecioService.java
        |-- PrecioServiceImpl.java
    |-- dto
        |-- PrecioRespuesta.java
    |-- model
        |-- Precio.java
    |-- repository
        |-- PrecioRepository.java
</pre>

* 'com.prueba.controller': Este paquete contiene los controladores de la aplicación, responsables de manejar las solicitudes HTTP, procesar los datos y enviar respuestas.

* 'com.prueba.service': Contiene interfaces y clases relacionadas con la lógica de negocio de la aplicación. La interfaz PrecioService.java define los métodos que deben ser implementados por las clases que proporcionan la funcionalidad relacionada con los precios, PrecioServiceImpl.java.

* 'com.prueba.dto': Este paquete contiene las clases DTO (Data Transfer Objects) que se utilizan para transferir datos entre diferentes capas de la aplicación.

* 'com.prueba.model': Este paquete contiene las clases que representan los modelos de datos de la aplicación. 

* 'com.prueba.repository': Este paquete se encarga de interactuar con la capa de persistencia de datos, utilizando Spring Data JPA.


A mayores se han empleado diferentes bibliotecas con el fin de simplicar el código. Ejemplos de ello son:
* Lombok. Proporciona anotaciones para generar automáticamente métodos y constructores estándar. 
* ModelMapper. Mapea objetos en Java simplificando el proceso de transferir datos entre modelos de dominio diferentes.
* ...

## PRUEBAS

Se ha decidido probar el controller y el repository para lograr una buena cobertura de código, y dado que apenas existe lógica de negocio (solo una operación de consulta).
Al probar el controlador se garantiza que las solicitudes HTTP se manejen correctamente y que las respuestas se generen de acuerdo con lo esperado.
Al probar el repositorio se puede verificar que las consultas de base de datos se ejecuten correctamente y obtienen los precios esperados.

Para lanzar los tests simplemente es necesario seleccionar la clase PrecioControllerTest.java o PrecioRepositoryTest y luego la opción 'Run As > Junit Test'. Esto puede variar en función del IDE empleado

## ENTORNO

Como IDE de desarrollo se ha empleado Eclipse. Familiarizado también con Intellij y NetBeans


## MEJORAS

Se ha priorizado realizar el ejercicio de forma completa, creando los tests necesarios, generando javadoc....sobre una implementación compleja del modelo.
Este escenario se podría haber modelado relacionando la tabla de precios con otras tablas de productos, marcas...
