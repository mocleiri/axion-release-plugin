package pl.allegro.tech.build.axion.release

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import pl.allegro.tech.build.axion.release.domain.Releaser
import pl.allegro.tech.build.axion.release.domain.VersionConfig
import pl.allegro.tech.build.axion.release.infrastructure.di.Context
import pl.allegro.tech.build.axion.release.infrastructure.di.GradleAwareContext

class CreateReleaseTask extends DefaultTask {

    @Optional
    VersionConfig versionConfig

    @TaskAction
    void release() {
        VersionConfig config = GradleAwareContext.configOrCreateFromProject(project, versionConfig)
        Context context = GradleAwareContext.create(project, config)
        Releaser releaser = context.releaser()
        releaser.release(context.rules(), config)
    }

}
