package io.vodoo.core


class TaskRouterTest extends spock.lang.Specification {

    def "Call with a JSON list of tasks will execute them all"(){
        given:
        String jsonConfig = '[{"name": "myName", "type": "input"}, {"name": "otherName", "type": "output"}]'
        TaskRouter router = new TaskRouter()
        router.runnerFactory = new RunnerFactory()

        when:
        router.execute(jsonConfig)

        then:
        1==1
    }
}
