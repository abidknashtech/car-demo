#!/bin/bash

cwd=`pwd`
cd common
mvn -B clean deploy --file pom.xml