package nebula.plugin.responsible

import nebula.plugin.contacts.ContactsPlugin
import nebula.plugin.dependencylock.DependencyLockPlugin
import nebula.plugin.info.InfoPlugin
import nebula.plugin.publishing.maven.MavenPublishPlugin
import nebula.plugin.publishing.publications.JavadocJarPlugin
import nebula.plugin.publishing.publications.SourceJarPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.api.tasks.testing.Test

/**
 * Provide a responsible environment for a Gradle plugin.
 */
class NebulaResponsiblePlugin implements Plugin<Project> {
    protected Project project

    @Override
    void apply(Project project) {
        this.project = project

        // Publishing
        project.plugins.apply(MavenPublishPlugin)
        project.plugins.apply(JavadocJarPlugin)
        project.plugins.apply(SourceJarPlugin)

        // Info
        project.plugins.apply(InfoPlugin)

        // Contacts
        project.plugins.apply(ContactsPlugin)

        // Dependency Locking
        project.plugins.apply(DependencyLockPlugin)

        // TODO Publish javadoc somehow
        project.tasks.withType(Javadoc) {
            failOnError = false
        }
        project.tasks.withType(Test) { Test testTask ->
            testTask.testLogging.exceptionFormat = 'full'
        }
    }
}