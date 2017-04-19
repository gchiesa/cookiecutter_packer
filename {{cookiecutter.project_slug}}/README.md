# {{ cookiecutter.project_name }}
{{ cookiecutter.project_short_description }}

## Requirements
Please check the files under ```resources/requirements.*``` to gather all the 3rd party component
used by the image

## Usage
- Use the ```variables.json``` to override the defaults in ```packer.json```
- Use the ```_mob_ci/scripts/*``` helpers to test/build the code

## Notes
In order to build in a unattended way you may need to export the __AWS_ACCESS_KEY_ID__ 
and __AWS_SECRET_ACCESS_KEY__

When no environment variables are set, the ```_mob_ci/scripts/*``` will try to lookup the credentials
using profile name. You can pass the profile name to each script as 1st argument.

eg. 

    ./_mob_ci_scripts/build.sh devops

# Author
{{ cookiecutter.full_name }} <mailto:{{ cookiecutter.email }}>
