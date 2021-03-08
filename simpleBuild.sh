#!/bin/bash

# remove target txt if already exist
rm target.txt

# create target txt
printf "create from server\nSome random content" > target.txt

# rebuild and deploy docker for python file
