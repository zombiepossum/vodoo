package io.vodoo.core

import groovy.json.JsonSlurper
import io.vodoo.runners.InputRunner
import io.vodoo.runners.OutputRunner
import spock.lang.Specification


class RunnerFactoryTest extends Specification {

    RunnerFactory factory
    Runner expectedInputRunner, expectedOutputRunner, expectedDefaultRunner

    def setup(){
        factory = new RunnerFactory()
        factory.runners = [input: InputRunner, output: OutputRunner]
    }

    def "Create returns matching type in runner"(){
        given:
        def slurper = new JsonSlurper()
        def json = slurper.parseText('{"name": "myName", "type": "input"}')

        when:
        def runner = factory.create(json)

        then:
        runner instanceof InputRunner
        runner.config.is(json)
    }

    def "Create returns default if no matching type"(){
        given:
        def slurper = new JsonSlurper()
        factory.runners = [input: InputRunner]
        factory.defaultRunner = OutputRunner
        def json = slurper.parseText('{"name": "myName", "type": "foo"}')

        when:
        def runner = factory.create(json)

        then:
        runner instanceof OutputRunner
        runner.config.is(json)
    }
}
