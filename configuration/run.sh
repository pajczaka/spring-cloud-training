#!/bin/bash
while ! `nc -z $KAFKA_SERVER $KAFKA_PORT`;
    do sleep $SLEEP_TIME; echo "Waiting for kafka server";done
java -jar -Dspring.profiles.active=$DEFAULT_PROFILE configuration-server.jar