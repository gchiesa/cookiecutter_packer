#!/usr/bin/env groovy
import groovy.json.JsonSlurperClassic

def jobMap = new JsonSlurperClassic().parseText(readFileFromWorkspace('_mob_ci/configs/jobs_map.json'))
def defaultBaseImage = '{{ cookiecutter.base_image }}'

// for each job tuple it will create a new pipeline on jenkins
jobMap.each { jName, jPath ->
    pipelineJob(jName) {
        concurrentBuild(false)
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url('{{cookiecutter.jenkins_git_repository_clone_url }}')
                            credentials('{{ cookiecutter.jenkins_git_credentials_id }}')
                        }
                    }
                }
                scriptPath(jPath + '/Jenkinsfile')
            }
        }
        parameters {
            stringParam('codePath', jPath, 'Path of the packer directory')
            stringParam('currentBase', defaultBaseImage, 'Current base AMI')
        }
    }
}
