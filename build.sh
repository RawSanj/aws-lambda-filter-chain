#!/usr/bin/env bash

echo "======================== " `date` " ========================"

SKIP_BUILD="--skipBuild"

if [[ "$2" != "$SKIP_BUILD" ]];
    then
        printf "\n\n >>>>>>>>>>>>>>>>>>>> Building Library and Function <<<<<<<<<<<<<<<<<<<< \n\n"
        mvn clean install

        cd api-gateway-lambda-func

        printf "\n\n >>>>>>>>>>>>>>>>>>>> Packaging API Gateway Lambda Function <<<<<<<<<<<<<<<<<<<< \n\n"

        mvn clean package shade:shade

        printf "\n\n >>>>>>>>>>>>>>>>>>>> Packaging Completed. You can upload the jar file in AWS Console. <<<<<<<<<<<<<<<<<<<< \n\n"

        cd ..

fi

RUN_LOCALLY=runLocally

if [[ "$1" == "$RUN_LOCALLY" ]];
    then
        printf "\n\n >>>>>>>>>>>>>>>>>>>> Starting Local API Gate with Lambda Function <<<<<<<<<<<<<<<<<<<< \n\n"
        sam local start-api -t api-gateway-lambda-func/template.yaml
fi
