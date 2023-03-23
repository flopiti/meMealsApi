#!/bin/bash
git fetch
git reset --hard HEAD
git checkout origin/master
chown -R deployer:deployer .
docker compose down
docker compose up -d --build