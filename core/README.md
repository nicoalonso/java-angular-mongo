# Library Backend

Este es el backend de la aplicación de biblioteca.

## Tecnologías

- Java con el Framework Spring Boot
- MongoDB como BD
- RabbitMQ como capa de transporte
- Arquitectura Hexagonal

## Swagger

La documentación de la API se encuentra disponible en la ruta `/v1/doc` una vez que el proyecto esté en marcha.

Si has configurado el fichero `/etc/hosts` como se indica en el README principal, podrás acceder a la documentación de la API en la siguiente URL: [http://library-core.portafolio.loc/v1/doc](http://library-core.portafolio.loc/v1/doc)

## Mensajería

Vas a poder observar que usamos dos sistemas de mensajería:

- `ApplicationEventPublisher` del framework SpringBoot para disparar eventos en primer plano, es decir, dentro del mismo proceso de ejecución. Esto es útil para tareas que no requieren una comunicación entre servicios o procesos separados.
- `AmqpTemplate`, también del framework SpringBoot, para disparar procesos en segundo plano, es decir, fuera del proceso de ejecución principal. Esto es útil para tareas que requieren una comunicación entre servicios o procesos separados, como el envío de correos electrónicos o la generación de informes.

Para el segundo caso se usa como capa de transporte **RabbitMQ**.

## RabbitMQ - Configuración inicial

En principio cuando se levanta el proyecto, se debería de crear el exchange y la cola de RabbitMQ automáticamente, pero si ves que no se han creado, puedes crear el exchange y la cola de RabbitMQ.

Siga los siguientes pasos para configurar RabbitMQ:

1. Entra en la interfaz web de RabbitMQ en [http://localhost:15672](http://localhost:15672) con el usuario `guest` y la contraseña `guest`.

2. En la sección de "Exchanges", crea un nuevo exchange con el nombre `library` y el tipo `topic`.

3. En la sección de "Queues", crea una nueva cola con el nombre `library`.

4. En la sección de "Bindings", vincula la cola `library` al exchange `library` con la clave de enrutamiento `app.library`.

## Tests Unitarios

## Testing

Para la parte de testing se está usando JUnit como framework de pruebas unitarias.

Para ejecutar los tests unitarios, puedes usar el siguiente comando:

```bash
docker compose exec core gradle :test
```
