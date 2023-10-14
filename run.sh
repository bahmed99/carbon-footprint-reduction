#!/bin/bash

./run/run_server.sh &
./run/run_client.sh &

trap 'exit 0' TERM
trap 'kill -- -$$' INT

read -d ''