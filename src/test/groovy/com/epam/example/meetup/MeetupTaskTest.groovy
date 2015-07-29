package com.epam.example.meetup

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.After
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.assertTrue

class MeetupTaskTest{

    File file
    Project project
    MeetupTask task;

    @Before
    void setup() {
        project = ProjectBuilder.builder().build()
        task = project.task('addLicensesTest', type: MeetupTask)
    }

    @After
    void tearDown() {
        if (file != null) {
            file.delete()
        }
        file = null
        task = null
    }

    @Test
    void runTest() {

    }

    @Test
    void addLicenseTest() {
        file = new File(project.projectDir, "test.java")
        System.out.println(file.path)
        file.createNewFile()
        assertTrue("File doesn't exist", file.exists())
        String license = "LICENSE"

        task.addLicense(file, license)

        assertTrue(file.text.contains("LICENSE"))
    }

}
