#!/bin/bash

cwd=`pwd`
cd Common
mvn -B clean deploy --file pom.xml