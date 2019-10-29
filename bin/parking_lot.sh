#!/usr/bin/env bash

SCRIPT_DIR="$(dirname "${BASH_SOURCE[0]}")"
cd "${SCRIPT_DIR}"
cd ..

JAR_RELATIVE_PATH=target/parking-lot-1.0-SNAPSHOT.jar

if [ -z "$1" ] ; then
        java -jar $JAR_RELATIVE_PATH
        exit 1

else
  inputFile=$1
	java -jar $JAR_RELATIVE_PATH $inputFile
fi
