#!/bin/bash

MYSQL_IMAGE="mysql:latest"

MYSQL_CONTAINER="db"

MYSQL_ROOT_PASSWORD="root"

DB_NAME="db"

SQL_FILE_PATH="./data.sql"



docker cp $SQL_FILE_PATH $MYSQL_CONTAINER:/tmp/

docker exec -it db mysql -u root -p$MYSQL_ROOT_PASSWORD $DB_NAME -e "source /tmp/data.sql"



