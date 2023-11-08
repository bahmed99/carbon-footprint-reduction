#!/bin/bash

#verif if param is passed

# if [ $# -eq 0 ]
# then
#     echo "No arguments supplied"
#     exit 1
# fi



# param="$1"



# if [ "$param" = "docker" ]
# then

docker compose up  &

sleep 5

./run/insert.sh &

./run/run_server.sh &
./run/run_client.sh &

# else


# ./run/run_server.sh &
# ./run/run_client.sh &

# fi



trap 'exit 0' TERM
trap 'kill -- -$$' INT

read -d ''