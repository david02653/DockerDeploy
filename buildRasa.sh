#!/bin/bash

# environment variable declare
PROJECT_HOME=~/Desktop/Workspace/DockerDeploy
RESOURCE_DIR=/src/main/resources
SETTING_DIR=/result
#JAR_PATH=/target/demo-0.jar
RASA_DIR=~/Desktop/Workspace/Rasa/RasaTest
NLU=/data/nlu.md
DOMAIN=/domain.yml
STORIES=/data/stories.md
ACTION=/actions/actions.py

# override rasa setting with new configuration
cp $PROJECT_HOME$RESOURCE_DIR$SETTING_DIR/nlu.md $RASA_DIR$NLU
cp $PROJECT_HOME$RESOURCE_DIR$SETTING_DIR/domain.yml $RASA_DIR$DOMAIN
cp $PROJECT_HOME$RESOURCE_DIR$SETTING_DIR/stories.md $RASA_DIR$STORIES
cp $PROJECT_HOME$RESOURCE_DIR$SETTING_DIR/actions.py $RASA_DIR$ACTION

# redeploy rasa docker
sh $RASA_DIR/build.sh