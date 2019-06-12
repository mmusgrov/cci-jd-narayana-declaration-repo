pipelineJob(ITEM_PATH){
  description("Autogenerated job from Jenkinsfile")
  logRotator {
    numToKeep(10)
  }
  definition {
    cps {
      script(PIPELINE_SCRIPT)
      sandbox()
    }
  }
}
