#!/bin/sh
mvn clean assembly
java -jar target/log-demo.jar
