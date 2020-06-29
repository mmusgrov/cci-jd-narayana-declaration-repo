// Example Job DSL definition for declaration repository CI

multibranchPipelineJob(ITEM_PATH) {
    branchSources {
        git {
            id 'id'
            // To be replaced by _actual_ declaration repository url
            remote("https://gitlab.cee.redhat.com/ccit/integration/cci-jd-example-declaration-repo.git")
        }
    }

    factory {
        workflowBranchProjectFactory {
            // Path to Jenkinsfile inside declaration repository
            scriptPath('Jenkinsfile')
        }
    }

    orphanedItemStrategy {
        discardOldItems {
            daysToKeep(3)
        }
    }
}
