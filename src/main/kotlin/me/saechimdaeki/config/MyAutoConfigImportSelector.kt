package me.saechimdaeki.config

import org.springframework.context.annotation.DeferredImportSelector
import org.springframework.core.type.AnnotationMetadata

class MyAutoConfigImportSelector : DeferredImportSelector {
    override fun selectImports(importingClassMetadata: AnnotationMetadata): Array<String> {
        return arrayOf(
            "me.saechimdaeki.config.autoconfig.DispatcherServletConfig",
            "me.saechimdaeki.config.autoconfig.TomcatWebServerConfig"
        )
    }
}