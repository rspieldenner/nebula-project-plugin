package nebula.plugin.responsible

import nebula.test.functional.ExecutionResult
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Runs Gradle Launcher style integration Spock tests on the NebulaIntegTestPlugin class
 */
class NebulaIntegTestPluginLauncherSpec extends AbstractNebulaIntegTestPluginLauncherSpec {
    @Override
    Class<Plugin<Project>> getPluginClass() {
        return NebulaIntegTestPlugin.class
    }

    def "check depends on integration test task"() {
        when:
        ExecutionResult result = runTasksSuccessfully('check')

        then:
        result.wasExecuted(':test')
        result.wasExecuted(':integrationTest')
    }
}
