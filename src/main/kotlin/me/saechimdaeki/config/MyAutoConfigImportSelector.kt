package me.saechimdaeki.config

import org.springframework.boot.context.annotation.ImportCandidates
import org.springframework.context.annotation.DeferredImportSelector
import org.springframework.core.type.AnnotationMetadata

class MyAutoConfigImportSelector(private val classLoader: ClassLoader) : DeferredImportSelector {
    override fun selectImports(importingClassMetadata: AnnotationMetadata): Array<String> {

        val autoConfigs = arrayListOf<String>()

        for (candidate in ImportCandidates.load(MyAutoConfiguration::class.java, classLoader)) {
            autoConfigs.add(candidate)
        }

        return autoConfigs.toTypedArray()


//        @Suppress("UNCHECKED_CAST")
//        return StreamSupport.stream(candidates.spliterator(), false).toArray() as Array<String>

//        return arrayOf(
//            "me.saechimdaeki.config.autoconfig.DispatcherServletConfig",
//            "me.saechimdaeki.config.autoconfig.TomcatWebServerConfig"
//        )
    }
}