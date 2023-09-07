#!/bin/bash

cwd=`pwd`
cd Common

mvn clean deploy --file pom.xml