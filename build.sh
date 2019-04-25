#!/bin/sh
docker rmi $(docker images -q | grep "^training")
docker rmi $(docker images -q | grep "<none>")

cd configuration
mvn clean
mvn package
docker build -t "training/configuration-server" .
cd ..
