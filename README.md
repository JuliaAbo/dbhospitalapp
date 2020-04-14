# dbhospitalapp

##To run project:

Run create schema for mydb2, which was submitted as a sql file called mydb2.sql
Ensure you can connect and log into mySQL to do this.
Navigate to the folder that holds the .jar file from our project
Type: java -jar dbhospitalapp.jar

Libraries used: 
mysql-connector-java-8.0.19.jar

Needed: 
Java JDK for Java 11: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
mysql  Ver 8.0.19 on x86_64 (MySQL Community Server - GPL)

Built in Java 11. 

Architecture of project: 
- rundatabase: the main function which will ask for user / password and launch the app
- HospitalApp: The application that takes in user input and dynamically performs actions based on it
    - commands: A HashMap of String commands -> Function objects which dictates what the app should perform given user input
- functions directory: Houses all the function objects to dictate behavior. Uses the factory design pattern to build an instance of the desired HashMap<String, IFunc>

 