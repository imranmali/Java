# SpringMVC4-MultiFileUpload-Download-Rest-Database-Example

## SpringMVC 4, Hibernate 4, MySQL 5, and java 8

##Main Purposes

###Upload multiple file and store into the database and download file 
It's cloud deploy-able (jetty-runner.jar + compiled war file)
For configuration of Spring MVC, it uses Java config instead of xml config.
For Hibernate 4 and MySQL, please modify src/main/resources/application.properties file
Build and run the app in command line environment

###1.Create table in MySQL

Copy the text in src/main/resources/db_script.sql and run it to create a table in MySQL (in phpmyadmin or MySQLworkbench or mysql command line environment)

###2.Build package
```
$ mvn package
```
###3.Run jetty-runner
```
$ ./run.sh
or
$ java -jar jetty/jetty-runner-9.2.6.v20141205.jar --port 8090 --log jetty.log target/SpringFileUpload.war
```
###Test it

Go to http://localhost:8090/SpringFileUpload and test it.

