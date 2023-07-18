#!/bin/sh
mvn clean test

value=`cat target/temp.txt`
curl -X POST http://195.2.79.224:5005/test/$value/send-tg \
        -H "Content-Type: application/json" \
        -d '{"chat_id": "-1001978872241", "bot_token": "5856518056:AAEBDRKvwaMFruGX20OkY3bB6K-h5z68E-4", "name": "ponimayu-ui"}'