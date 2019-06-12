freeStyleJob(ITEM_PATH) {
   scm {
     github('https://github.com/jenkinsci/openstack-cloud-plugin', 'master')
   }
   steps {
     shell('/qa/tools/opt/maven-3.6.0/bin/mvn -B clean cobertura:cobertura -Dcobertura.report.format=xml -Dmaven.test.failure.ignore=true')
   }
   publishers {
     archiveJunit('**/target/surefire-reports/*.xml')
   }
}
