package io.vodoo.core


class RunnerFactory {
    Map<String, Class<Runner>> runners = [:]
    Class<Runner> defaultRunner

    Runner create(def config) {
        String type = config.type
        Class runnerClass = runners[type] ?: defaultRunner
        Runner runner =  runnerClass.newInstance()

        runner.config = config

        return runner
    }
}
