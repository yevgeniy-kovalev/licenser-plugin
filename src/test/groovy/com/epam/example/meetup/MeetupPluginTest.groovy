package com.epam.example.meetup

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertTrue

class MeetupPluginTest {

    Project project

    @Before
    void setup() {
        project = ProjectBuilder.builder().build()
        Plugin plugin = new MeetupPlugin()
        plugin.apply(project)
    }

    @Test
    public void licenserPluginAddsToProject() {
        assertTrue(project.tasks.addLicenses instanceof MeetupTask)
        assertTrue(project.tasks.addLicenses.group.equalsIgnoreCase("MeetupGroup"))

        project.license {

        }

        assertTrue(project.extensions.license != null)
    }

}
