spring-boot-batch-sample
========================

Spring Boot Batch Sample Application

This spring-boot application demonstrates the following techniques:

1. Using SpringBoot to create applications with embedded tomcat container
2. Using GORM to create domains and query from domain
3. Using Groovy Bean DSL to wire batch job dependencies (instead of annotation driven approach): @see app-context.groovy
4. Schedule is done using Spring scheduler and executed via annotation configuration
5. Properties provided for both H2 and MySql driver connections
6. Using spring-batch-admin to expose job service and job operations and display job status in a simple UI
7. A simple UI to display number job status 
8. Starts H2 server and allows viewing db via localhost:8082
9. Uses Config.groovy for non-spring-standard properties (spring standard properties go into application.properties)
10. Uses logback.groovy for logging configuration
11. Uses Bootstrap class for creating test data for the domain

Functionality

Read StarCatalog data from database and extract data to csv file

Versions

@see build.gradle for all the versions used

Gotchas

spring-batch-admin:1.2.2.RELEASE does not work with spring-boot as of this day. Had to use spring-batch-admin:1.3.0.M1

Future

1. Add AngularJS: Retrieve job status from spring-batch-admin as a json body, use AngularJS to display
2. Start/Stop Jobs from UI
3. Add SpringSecurity
4. Multiple datasources
5. Spring-loaded for hotswap (doesn't work currently)

Blog

Behind the scenes: http://vasya10.wordpress.com/2014/05/03/the-groovyspringbootbatchgormgroovydslbeanfactory/

