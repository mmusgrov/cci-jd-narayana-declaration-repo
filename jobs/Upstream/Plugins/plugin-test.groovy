def plugins = ["git-plugin","openstack-cloud-plugin","mailer-plugin","ssh-slaves-plugin"]
for(String plugin : plugins){
    pipelineJob(FOLDER_PATH + "/" + plugin){
        definition {
            cps {
                script("""
                          node() {
                             git "https://github.com/jenkinsci/$plugin"
                             sh '/qa/tools/opt/maven-3.6.0/bin/mvn -B clean cobertura:cobertura -Dcobertura.report.format=xml -Dmaven.test.failure.ignore=true'
                             junit '**/target/surefire-reports/**/*.xml'
                             cobertura([
                               autoUpdateHealth: false,
                               autoUpdateStability: false,
                               coberturaReportFile: '**/target/site/cobertura/coverage.xml',
                               failUnhealthy: false,
                               failUnstable: false,
                               maxNumberOfBuilds: 0,
                               onlyStable: false,
                               sourceEncoding: 'ASCII',
                               zoomCoverageChart: false
                            ])
                         }

                   """)
                sandbox()
            }
        }
    }

}
