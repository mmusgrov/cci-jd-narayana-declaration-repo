// Example Jenkinsfile for _multibranch_ pipeline job

String GITLAB_CONNECTION_NAME = 'change-me'
String JENKINS_LABEL = 'change-me'

properties([gitLabConnection(GITLAB_CONNECTION_NAME)])
try {
    node(JENKINS_LABEL) {
        checkout scm
        dir("jcasc"){
            git "https://gitlab.mw.lab.eng.bos.redhat.com/jbossqe-jenkins/jcasc.git"
        }
        withEnv([
                "JCASC_DIR=${env.WORKSPACE}/jcasc",
                "CONFIG_DIR=${env.WORKSPACE}"
        ]) {
            sh "jcasc/test/test.sh"
        }
    }
    updateGitlabCommitStatus(state: 'success')
} catch (Exception ex) {
    updateGitlabCommitStatus(state: 'failed')
    throw ex
}
