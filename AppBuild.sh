#!/bin/bash

# shellcheck disable=SC2039
package_project=${1:true}
tag=${2:latest}

if [ "$package_project" = true ]; then
  echo 'Building ABN App with Maven'
  ./mvnw clean package -DskipTests
  echo '----------------------------------------------------------------------------------'
  echo
fi


echo 'Building ABN App Docker Image'
docker build -t ghcr.io/MikeBenton2/abnrecipeapi/abnrecipeapi:"$tag" -f ./infra/docker/DockerfileApp .
echo '----------------------------------------------------------------------------------'