#!/bin/bash

# create/edit/write target txt
# use shell bash to build new image and deploy

# remove current docker if exist
sudo docker rm $(sudo docker ps -a -q --filter="name=pytest")
# copy target.txt to docker source
yes | cp target.txt /var/lib/docker/volumes/dataspace/_data/target.txt
# move to directory
cd /home/yuwen/Desktop/docker/python-test
# build new image
sudo docker image build -t pytest .
# build new container
sudo docker run -d --name pytest pytest