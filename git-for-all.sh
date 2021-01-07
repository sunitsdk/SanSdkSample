#!/bin/bash

# 打印文件名称  git-for-all.sh
echo $0
# 打印传入参数数  fetct rebase 
echo $*

# must have git commands
if [ $# -eq 0 ]
then
	echo USAGE: $0 git-command git-command-parameters ...
	exit 0
fi

#git@gitlab.ushareit.me:android/ads/OpenSDKDemo.git

#clone项目相关仓库
gitCommand=$*
COMMAND=$1
PARAMS=$2

# ./tools.sh cloneAll
REPO_PATH_ARRAY=(
   "git@gitlab.ushareit.me:android/ads/ShareAd.git"
   "git@gitlab.ushareit.me:android/ads/LotusEmpty.git"
)
if [ "$COMMAND" == clone ];then

    if [ "$PARAMS" == -a ];then

      for i in "${!REPO_PATH_ARRAY[@]}"; do
        echo "clone ${REPO_PATH_ARRAY[$i]}"
        git clone "${REPO_PATH_ARRAY[$i]}"
        echo
      done

      echo "clone all success"

    else
        git $gitCommand
    fi

    #clone完成后退出
    exit 0
fi

#git命令批处理
ROOT=$(pwd)
gitkStr="gitk"

call_git_command()
{
    #目录名为空时，不进入目录
	if [ -n "$1" ];then
	    echo ----------------[ $1 ]
	    cd $1
	else
        echo ----------------[ ${PWD##*/} ]
	fi

	# gitcommand 是否包含 gitk
	if [[ $gitCommand =~ $gitkStr ]]
	then
  		$gitCommand
	else
  		git $gitCommand
	fi

	# git $input
	cd $ROOT	
}

# find all git projects paths
PROJECTS=$(find . -maxdepth 3 -type d -name ".git" -a -not -path "./.repo/*" -print | sed 's/\/.git//')

# for each git project, do command
for P in $PROJECTS
do {
	call_git_command ${P:2}
}
done

echo ##########################################

