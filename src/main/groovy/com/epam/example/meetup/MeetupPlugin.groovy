package com.epam.example.meetup
import org.gradle.api.Plugin
import org.gradle.api.Project

class MeetupPlugin implements Plugin<Project> {

    private static final String TASK_NAME = 'addLicenses'
    public static final String EXT_NAME = 'license'

    @Override
    void apply(Project project) {
        project.extensions.create(EXT_NAME, MeetupExtension)
        MeetupTask task = project.task(TASK_NAME, type: MeetupTask)
        task.group = 'MeetupGroup'
        task.description = 'Adds/replaces existing license with the new one'
    }

}
