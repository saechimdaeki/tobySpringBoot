package me.saechimdaeki.config.autoconfig

@MyConfigurationProperties(prefix = "server")
data class ServerProperties(
    val contextPath: String?,
    val port: Int?
) {
}