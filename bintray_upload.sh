#!/bin/bash

Command=$1

REPO_PATH_ARRAY=(
    "ShareAd/AdSDK"

    "ShareAd/SUnit-Mediation/AdColony"
    "ShareAd/SUnit-Mediation/Admob"
    "ShareAd/SUnit-Mediation/AppLovin"
    "ShareAd/SUnit-Mediation/FacebookAudienceNetwork"
    "ShareAd/SUnit-Mediation/Fyber"
    "ShareAd/SUnit-Mediation/IronSource"
    "ShareAd/SUnit-Mediation/TopOn"
    "ShareAd/SUnit-Mediation/Mopub"
    "ShareAd/SUnit-Mediation/UnityAds"
    "ShareAd/SUnit-Mediation/Vungle"
)
work_path=$(pwd)
is_dryRun=false

if [ "$Command" == publish ];then
    rm -f $work_path/publish_result.txt
    for i in "${!REPO_PATH_ARRAY[@]}"; do
        echo ${zREPO_PATH_ARRAY[$i]}
        cd ${REPO_PATH_ARRAY[$i]}
        cur_path=$(pwd)
        pwd
        echo
        gradle clean build bintrayUpload -PdryRun=$is_dryRun >> $work_path/publish_result.txt
        echo
        if [ $is_dryRun == false ]; then
          echo "$cur_path' Sleeping"
        fi
        echo "Publish '$cur_path' Complete"

        cd $work_path

    done
    #clone完成后退出
    exit 0
fi

echo ##########################################

