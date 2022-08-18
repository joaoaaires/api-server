SET SONAR=C:\Programas\sonar-scanner

SET PATH=%PATH%;%SONAR%\bin

@ECHO OFF
CALL mvn clean compile install

sonar-scanner