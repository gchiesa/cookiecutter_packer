#!/usr/bin/env bash
set -e

# load the .aws profile exporting the variables to connect to aws
load_profile() {
    if [ -z "$1" ]; then
        echo "You must specify a profile or export AWS_ACCESS_KEY_ID / AWS_SECRET_ACCESS_KEY"
        exit 1
    fi
    local cred=($(grep -A2 -e "\[$1\]" ~/.aws/credentials))
    local key=${cred[1]}
    local secret=${cred[2]}
    export AWS_ACCESS_KEY_ID=${key#*=}
    export AWS_SECRET_ACCESS_KEY=${secret#*=}
}

# move to base folder
cd $(dirname $0)/../..

if [ -z "$AWS_ACCESS_KEY_ID" -o -z "$AWS_SECRET_ACCESS_KEY" ]; then
    load_profile $1
fi

packer validate packer.json
packer build -var-file variables.json packer.json
