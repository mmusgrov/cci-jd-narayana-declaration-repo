// Example Jenkinsfile for _multibranch_ pipeline job

String GITLAB_CONNECTION_NAME = 'change-me'
String JENKINS_LABEL = 'change-me'

properties([gitLabConnection(GITLAB_CONNECTION_NAME)])
try {
    node(JENKINS_LABEL) {
        checkout scm
        dir("jenkins-csb"){
            git url: "https://gitlab.cee.redhat.com/ccit/jenkins-csb.git", branch: "2-190-3"
        }
        withEnv([
                "JCASC_DIR=${env.WORKSPACE}/jenkins-csb/cci-jd",
                "CONFIG_DIR=${env.WORKSPACE}"
        ]) {
            sh "jenkins-csb/cci-jd/test/test.sh"
        }
    }
    updateGitlabCommitStatus(state: 'success')
} catch (Exception ex) {
    updateGitlabCommitStatus(state: 'failed')
    throw ex
}
