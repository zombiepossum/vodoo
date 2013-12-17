package io.vodoo.core

import groovy.json.JsonSlurper


class TaskRouter {

    RunnerFactory runnerFactory

    void execute(String config){
        def slurper = new JsonSlurper()
        def result = slurper.parseText(config)
        result.each {
            Runner runner = runnerFactory.create(it)
            runner.executeTask()
        }
    }

}
