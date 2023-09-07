#!/bin/bash

cwd=`pwd`
cd Common

mvn -B clean deploy -s $GITHUB_WORKSPACE/settings.xml --file pom.xml