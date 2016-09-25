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

#Steps to set up development environment in Eclipse

##After you clone this project from github, run
```
$ mvn package
$ mvn eclipse:eclipse
```
Open Eclipse to import the project
Click top File -> Import -> Maven -> Existing Maven Projects (Choose the folder that you just downloded)
Right click your project, click Properties -> Java Build Path, remove all M2_REPO/***/*** jars (names started with M2_REPO), click OK.
Check if Maven dependencies are setup correctly. Right click your project, click Properties -> Java Build Path-> Libraries, and the Maven Dependencies include all your dependent jar files
Right click pom.xml, click Run as-> Maven clean, then Run as-> Maven Install
Right click your project, click Run as -> Run on server (Supposed you already set up your local Tomcat server)


Development environment

Windows 10 64bit, STS 3.6.2.RELEASE
