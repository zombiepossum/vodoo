package io.vodoo.core

import io.vodoo.runners.InputRunner
import io.vodoo.runners.OutputRunner


class TaskRouterTest extends spock.lang.Specification {

    def "Call with a JSON list of tasks will execute them all"(){
        given:
        RunnerFactory mockFactory = Mock(RunnerFactory)
        Runner mockInputRunner = Mock(InputRunner)
        Runner mockOutputRunner = Mock(OutputRunner)
        String jsonConfig = '[{"name": "myName", "type": "input"}, {"name": "otherName", "type": "output"}]'
        TaskRouter router = new TaskRouter()
        router.runnerFactory = mockFactory
        router.runnerFactory.runners = [input: mockInputRunner, output: mockOutputRunner]

        when:
        router.execute(jsonConfig)

        then:
        1 * mockFactory.create({it.type == 'input'}) >> mockInputRunner
        1 * mockInputRunner.executeTask()
        1 * mockFactory.create({it.type == 'output'}) >> mockOutputRunner
        1 * mockOutputRunner.executeTask()
    }
}
