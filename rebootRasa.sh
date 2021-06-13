#!/bin/bash

# environment variable
PROJ_HOME=~/Desktop/Workspace/DockerDeploy
NEW_SETTING_DIR=/src/main/resources/result/v2/mergeData
RASA_DIR=~/Desktop/Workspace/Rasa/msabot_test_2.5/msdobotTest
FILE_BACKUP_DIR=/backup/script_rebuild
# new setting file
N_NLU=/nlu.yml
N_STORIES=/stories.yml
N_DOMAIN=/domain.yml
N_ACTION=/actions.py
backup_folder_name=$(date +%Y%m%d%H%M)
# current setting file
NLU=/data/nlu.yml
DOMAIN=/domain.yml
STORIES=/data/stories.yml
ACTION=/actions/actions.py

# backup current settings
mkdir -p $RASA_DIR$FILE_BACKUP_DIR/"$backup_folder_name"
cp $RASA_DIR$NLU $RASA_DIR$FILE_BACKUP_DIR/"$backup_folder_name"$N_NLU
cp $RASA_DIR$STORIES $RASA_DIR$FILE_BACKUP_DIR/"$backup_folder_name"$N_STORIES
cp $RASA_DIR$ACTION $RASA_DIR$FILE_BACKUP_DIR/"$backup_folder_name"$N_ACTION
cp $RASA_DIR$DOMAIN $RASA_DIR$FILE_BACKUP_DIR/"$backup_folder_name"$N_DOMAIN

# override settings
cp $PROJ_HOME$NEW_SETTING_DIR$N_NLU $RASA_DIR$NLU
cp $PROJ_HOME$NEW_SETTING_DIR$N_STORIES $RASA_DIR$STORIES
cp $PROJ_HOME$NEW_SETTING_DIR$N_ACTION $RASA_DIR$ACTION
cp $PROJ_HOME$NEW_SETTING_DIR$N_DOMAIN $RASA_DIR$DOMAIN

# move dir
# shellcheck disable=SC2164
cd $RASA_DIR

# activate vm for rasa
# maybe move this script into project local script
conda activate rasaspace

# debug message
echo activate conda
echo try to retrain model
# build new model
# maybe move this script into project local script
rasa train --fixed-model-name spacyModel

# debug message
echo restart docker

# clean and deploy new rasa docker
sh stop.sh
sh clean.sh
docker-compose up -d