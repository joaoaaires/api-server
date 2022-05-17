#!/usr/bin/env bash

export MAVEN_HOME="$HOME/Library/Maven"
export SONAR_HOME="$HOME/Library/SonarScanner"
export JAVA_HOME="$HOME/Library/Java/JavaVirtualMachinesOld/jdk-11.0.13.jdk/Contents/Home"

export PATH="$SONAR_HOME/bin:$PATH"
export PATH="$MAVEN_HOME/bin:$PATH"
export PATH="$JAVA_HOME/bin:$PATH"

mvn clean compile install

sonar-scanner