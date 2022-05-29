#!/bin/bash 

set -e

mvn clean package

docker build -t lintao0o0/keyboard-server:0.0.2 .
docker push lintao0o0/keyboard-server:0.0.2
