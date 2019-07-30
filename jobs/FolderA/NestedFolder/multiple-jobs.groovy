def plugins = ["git", "openstack-cloud", "mailer", "ssh-slaves"]
for (String plugin : plugins) {
    pipelineJob("${FOLDER_PATH}/${ITEM_NAME}.${plugin}") {
        definition {
            cps {
                script("""
                    node() {
                        git "https://github.com/jenkinsci/${plugin}-plugin"
                        sh 'cat pom.xml'
                    }
                """)
                sandbox()
            }
        }
    }
}
