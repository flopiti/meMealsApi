#!/bin/bash
export DB_USER=$1
export DB_PASSWORD=$2
export DB_ROOT_PASSWORD=$3
git fetch
git reset --hard HEAD
git checkout origin/main
chown -R deployer:deployer .
docker compose down
docker compose up -d --build