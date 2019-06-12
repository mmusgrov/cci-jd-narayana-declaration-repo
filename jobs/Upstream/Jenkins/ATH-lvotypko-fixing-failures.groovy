pipelineJob(ITEM_PATH){
  logRotator {
    numToKeep(10)
  }
  parameters {
    stringParam('DOWNLOAD_URL', 'http://mirrors.jenkins-ci.org/war-stable-rc/latest/jenkins.war', 'DOWNLOAD_URL')
    stringParam('TEST', '-Dtest=', 'Which tests should run')
    stringParam('BRANCH', 'master', 'Branch of source code repository')
    stringParam('INTERACTIVE', 'false', 'Run in interactive mode')
    stringParam('REPOSITORY', 'https://github.com/lvotypko/acceptance-test-harness/', 'ATH source code')
    choiceParam('BROWSER', ['firefox', 'chrome'], 'Browser for execution selenium tests')
  }
  definition {
    cpsScm {
      scm {
        git("https://gitlab.mw.lab.eng.bos.redhat.com/jbossqe-jenkins/cci-config.git", "master")
      }
      lightweight(true)
      scriptPath("jobs/${ITEM_PATH}.jenkinsfile")
    }
  }
}
