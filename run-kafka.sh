#!/bin/sh
docker run -p 8800:2181 -p 8001:9092 --env ADVERTISED_HOST=192.168.99.100 --env ADVERTISED_PORT=9092 spotify/kafka