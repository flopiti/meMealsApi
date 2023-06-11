#!/bin/bash
git fetch
git reset --hard HEAD
git checkout origin/main
chown -R deployer:deployer .
docker compose down
docker compose up -d --build