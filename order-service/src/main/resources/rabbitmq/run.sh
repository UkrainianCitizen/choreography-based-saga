#!/bin/bash

docker run -p 5672:5672 -p 15672:15672 -dti --name rabbitmq --hostname ahuba plugged-rabbitmq:1.0