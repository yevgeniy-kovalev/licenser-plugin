package com.epam.example.meetup
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.TaskAction

class MeetupTask extends DefaultTask {

    private static final String DEFAULT_PATH = 'src/main/java'

    @InputFile
    def license
    @InputFiles
    def layouts

    @TaskAction
    def run() {
        String path = project.license.path
        String pathToSrc = project.license.pathToSrc
        if (path?.length() > 0 && project.file(path) != null) {
            license = project.file(path)
            String license = license.text
            pathToSrc = pathToSrc?.length() > 0 ? pathToSrc : DEFAULT_PATH
            logger.info("path to src: ${pathToSrc}")
            layouts = project.fileTree(
                    dir: "${pathToSrc}",
                    includes: ['**/*.java'])
            logger.info("Size of found source files collection:${layouts.size()}")
            layouts.each { file ->
                String content = file.text.trim()
                if ( (content.startsWith("/*") && !content.contains(license))
                        || content.startsWith("package") ) {
                    addLicense(file, license)
                }
            }
        } else {
            logger.error("Please provide path for the task.")
        }
    }

    void addLicense(File target, String license) {
        String text = license + System.getProperty("line.separator") + target.text
        target.write(text)
    }

}
