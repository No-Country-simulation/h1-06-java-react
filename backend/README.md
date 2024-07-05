# h01-06-java-react

[//]: # (https://placeholderimage.dev/)
<p align="center">
  placeholder image
  <br/>
  <a href="https://nodejs.org">
    <picture>
      <img src="project-assets/images/readme-banner.png" width="400px">
    </picture>
  </a>
</p>

To run this version of the project, the following dependencies and/or configurations are required:

* MySql
* MySql Workbench (optional)
* Java 17+ Openjdk
* Maven 3.9.6 configured on the system or IDE configuration.

User account configuration and password are added in the `./src/main/resources/application.properties` file.

SQL statement to create the database using the command line or MySql Workbench:

```sql
CREATE SCHEMA `justinaio` DEFAULT CHARACTER SET utf8;
```

Run from the root directory:

```terminal
mvn clean spring-boot:run
```



