#!/usr/bin/env bash

export MAVEN_HOME="$HOME/Library/Maven"
export SONAR_HOME="$HOME/Library/SonarScanner"
export JAVA_HOME="$HOME/Library/Java/jdk-17.0.4.1.jdk/Contents/Home"

export PATH="$SONAR_HOME/bin:$PATH"
export PATH="$MAVEN_HOME/bin:$PATH"
export PATH="$JAVA_HOME/bin:$PATH"

mvn clean compile install

sonar-scanner