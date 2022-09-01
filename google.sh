#!/usr/bin/env bash

export MAVEN_HOME="$HOME/Library/Maven"
export GOOGLE_HOME="$HOME/Library/GoogleCLI"
export JAVA_HOME="$HOME/Library/Java/JavaVirtualMachinesOld/jdk-11.0.13.jdk/Contents/Home"

export PATH="$GOOGLE_HOME/bin:$PATH"
export PATH="$MAVEN_HOME/bin:$PATH"
export PATH="$JAVA_HOME/bin:$PATH"

mvn clean compile install -DskipTests=true

gcloud run deploy