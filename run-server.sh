#!/usr/bin/env bash
sbt server/docker
docker run -d -p 12345:8080 dr0l3/fts-server
