### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.

### What I've done
- Configure to use Spring Doc for API documentation.
- Add documentation.
- Add logging.
- Add tests for `EmployeeController`.

### What could be improved if I have more time
- Augmenting tests for other functions in `EmployeeController`, `EmployeeService`.
- Protect API endpoints by requiring an API key whenever client hit an endpoint. 
