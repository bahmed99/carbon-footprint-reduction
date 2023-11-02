#!/bin/bash

docker compose up  &


sleep 5

./run/insert.sh &

./run/run_server.sh &
./run/run_client.sh &

trap 'exit 0' TERM
trap 'kill -- -$$' INT

read -d ''