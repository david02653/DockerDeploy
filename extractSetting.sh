#!/bin/bash

# environment variable
PROJ_HOME=~/Desktop/Workspace/DockerDeploy
NEW_SETTING_DIR=/src/main/resources/result/v2/mergeData
RASA_DIR=~/Desktop/Workspace/Rasa/msabot_test_2.5/msdobotTest
CURRENT_TEMP_DIR=/src/main/resources/data/v2
# new setting file
N_NLU=/nlu.yml
N_STORIES=/stories.yml
N_DOMAIN=/domain.yml
N_ACTION=/actions.py
# current setting file
NLU=/data/nlu.yml
DOMAIN=/domain.yml
STORIES=/data/stories.yml
ACTION=/actions/actions.py

# get current setting file to merge folder from rasa folder
yes | cp -rf $RASA_DIR$NLU $PROJ_HOME$NEW_SETTING_DIR$N_NLU
yes | cp -rf $RASA_DIR$STORIES $PROJ_HOME$NEW_SETTING_DIR$N_STORIES
yes | cp -rf $RASA_DIR$ACTION $PROJ_HOME$NEW_SETTING_DIR$N_ACTION
yes | cp -rf $RASA_DIR$DOMAIN $PROJ_HOME$NEW_SETTING_DIR$N_DOMAIN

# put current setting file to current setting temp folder
yes | cp -rf $PROJ_HOME$NEW_SETTING_DIR$N_NLU $PROJ_HOME$CURRENT_TEMP_DIR$N_NLU
yes | cp -rf $PROJ_HOME$NEW_SETTING_DIR$N_STORIES $PROJ_HOME$CURRENT_TEMP_DIR$N_STORIES
yes | cp -rf $PROJ_HOME$NEW_SETTING_DIR$N_ACTION $PROJ_HOME$CURRENT_TEMP_DIR$N_ACTION
yes | cp -rf $PROJ_HOME$NEW_SETTING_DIR$N_DOMAIN $PROJ_HOME$CURRENT_TEMP_DIR$N_DOMAIN