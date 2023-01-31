SET SONAR=C:\Programas\sonar-scanner
SET MAVEN=C:\Programas\maven
SET JAVA=C:\Programas\java-jdk\jdk-17.0.5

SET PATH=%PATH%;%SONAR%\bin
SET PATH=%PATH%;%MAVEN%\bin
SET PATH=%PATH%;%JAVA%\bin

@ECHO OFF
CALL mvn clean compile install

sonar-scanner