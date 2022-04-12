# Ψրօʂεἶძεղ
**Online Trading Platform**

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development
and testing purposes.</br>
See deployment for notes on how to deploy the project on a live system.

### App built with
What things you need to install this App

1. Java 8 OpenJDK
2. Spring Boot v2.6.2
3. Spring Boot JPA Repository
4. Spring Boot Security
5. mySql 8.0.28
6. Thymeleaf
7. Bootstrap v5.1.3
8. Maven
9. JUnit 5
10. Log4j

### Installing
A step by step series of examples that tell you how to get a development env running:

1.Install Java:
https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:
https://maven.apache.org/install.html

3.Install SpringBoot:
https://spring.io/projects/spring-boot

4.Install mySql:
https://www.mysql.com/downloads/

After downloading and installing it, no specific setup required.


### App Data
You will have to set up your own data base.

Schemas are provided in folder :

```shell
/Doc
```

**CLASS DIAGRAM**

<img src="https://github.com/xGuix/Poseiden-Inc/blob/master/doc/Poseiden-DomainModel.jpg" alt="classDiagram"/>


**UML DATA**

<img src="https://github.com/xGuix/Poseiden-Inc/blob/master/doc/Poseiden-ModelPhysique.jpg" alt="dataDiagram"/>


**UML SCHEMA**

<img src="https://github.com/xGuix/Poseiden-Inc/blob/master/doc/Poseiden-ClassDiagram.jpg" alt="dataDiagram"/>


## Setup with Intellij IDE
1. Create project from Initialize: File > New > project > Spring Initialize
2. Add lib repository into pom.xml
3. Add folders
   - Source root: src/main/java
   - View: src/main/resources
   - Static: src/main/resource/static
4. Create database with name "demo" as configuration in application.properties
5. Run sql script to create table doc/data.sql


## Setup Database
You will have to set up SQL tables and Admin user if needed.

Scripts are provided in folders :

```shell
/doc
```

First run script for data structure

```shell
data.sql
```

Create a specific admin user or use {root} {rootroot} to execute run


## Run the App
Setup your config in application.properties file if wanted for eclipse Startup for exemple.

{application.properties} is blank for following startup

```shell
spring.datasource.username=
spring.datasource.password=
```

**Run Maven commande  with {root} or {adminUser}** <br>
Replace {root} with user and {rootroot} with password <br>
Launch app commande :

```shell
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.datasource.username=root --spring.datasource.password=rootroot"
```


## URL Access

Open your browser and get localhost:8080

```html
https://localhost:8080/
```

Shutdown server

```shell
Ctrl + C
```


### Testing
The existing tests need to be triggered from maven-surefire plugin while we try to generate the final executable jar file.<br>
Run the tests from maven, go to the folder that contains the pom.xml file and execute the below command.

* Run unit tests, use command:

```shell
mvn:test
```

* Run integration tests, use command:

```shell
mvn failsafe:integration-test
```

* Run all tests, use command:

```shell
mvn verify
```


### Reports
Maven site to get all reports:

- **SureFire Report** for all unit Tests.
- **Jacoco Report** for tests coverage.
- **SpotBugs Report** for find bugs.

Run build site, use command:

```shell
mvn site
```

Access file directory : `target/site`
Run the `index.html` in your web browser.


### Jacoco Coverage
Jacoco coverage is automatically done with tests.

Access file directory : `target/site/jacoco/index.html`