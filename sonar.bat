SET MAVEN_HOME=C:\Programas\Maven
SET JAVA_HOME=C:\Programas\Java\jdk-11.0.13
SET SONAR=C:\Programas\SonarScanner

SET PATH=%PATH%;%MAVEN_HOME%\bin
SET PATH=%PATH%;%JAVA_HOME%\bin
SET PATH=%PATH%;%SONAR%\bin

@ECHO OFF
CALL mvn clean compile install

sonar-scanner