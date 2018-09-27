# 201810_ExpoReal_MUC
Expo Real Munich 2018



docker run -d -p 5000:5000 --restart=always --name registry registry:2

docker run -p7000:7000 --rm --name karma-endpoint lifttechnology/endpoint-karma

docker build -t lifttechnology/endpoint-karma .
docker push lifttechnology/endpoint-karma:latest
