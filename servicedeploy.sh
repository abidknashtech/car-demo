#!/bin/bash

cwd=`pwd`
cd Common

mvn -B -e -X clean deploy --file pom.xml