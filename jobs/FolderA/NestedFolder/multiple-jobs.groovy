def plugins = ["git", "openstack-cloud", "mailer", "ssh-slaves"]
for (String plugin : plugins) {
    pipelineJob("${FOLDER_PATH}/${ITEM_NAME}.${plugin}") {
        definition {
            cps {
                script("""
                    node() {
                        git "https://github.com/jenkinsci/${plugin}-plugin"
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
