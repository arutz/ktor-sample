val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

val prometeus_version: String by project
val exposed_version: String by project
val h2_version: String by project
val koin_version: String by project
val koin_ksp_version: String by project

plugins {
  kotlin("jvm") version "1.9.22"
  id("io.ktor.plugin") version "2.3.7"
  id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
  id("com.google.devtools.ksp") version "1.9.10-1.0.13"
  id("application")
}

group = "org.slashdev"

version = "0.0.1"

application {
  mainClass.set("org.slashdev.ApplicationKt")

  val isDevelopment: Boolean = project.ext.has("development")
  applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

sourceSets.main { java.srcDirs("build/generated/ksp/main/kotlin") }

repositories {
  // Use Maven Central for resolving dependencies.
  mavenCentral()
  mavenLocal()
}

dependencies {
  implementation("io.ktor:ktor-server-core-jvm")
  implementation("io.ktor:ktor-server-double-receive-jvm")
  implementation("io.ktor:ktor-server-resources")
  implementation("io.ktor:ktor-server-caching-headers-jvm")
  implementation("io.ktor:ktor-server-cors-jvm")
  implementation("io.ktor:ktor-server-default-headers-jvm")
  implementation("io.ktor:ktor-server-partial-content-jvm")
  implementation("io.ktor:ktor-server-call-logging-jvm")
  implementation("io.ktor:ktor-server-metrics-jvm")
  implementation("io.ktor:ktor-server-metrics-micrometer-jvm")
  implementation("io.micrometer:micrometer-registry-prometheus:$prometeus_version")
  implementation("io.ktor:ktor-server-content-negotiation-jvm")
  implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
  implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
  implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
  implementation("com.h2database:h2:$h2_version")
  implementation("io.ktor:ktor-server-netty-jvm")
  implementation("ch.qos.logback:logback-classic:$logback_version")
  testImplementation("io.ktor:ktor-server-tests-jvm")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

  // Align versions of all Kotlin components
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
  // Koin
  implementation("io.insert-koin:koin-core:$koin_version")
  implementation("io.insert-koin:koin-annotations:$koin_ksp_version")
  ksp("io.insert-koin:koin-ksp-compiler:$koin_ksp_version")

  testImplementation("io.insert-koin:koin-test:$koin_version")
  testImplementation("io.insert-koin:koin-test-junit4:$koin_version")
  testImplementation("org.mockito:mockito-inline:4.8.0")
}

// Compile time check
ksp { arg("KOIN_CONFIG_CHECK", "true") }
