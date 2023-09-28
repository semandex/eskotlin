/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

plugins {
  id("org.jetbrains.kotlin.jvm") version "1.9.10"
  id("org.jetbrains.kotlin.plugin.noarg") version "1.9.10"

  id("net.semandex.plugins.gradle-scripts-plugin") version "0.4.6"
}

description = "eskotlin project"
group "mbuhot"
version "1.1.0beta1"

val log4jVersion: String by extra("2.20.0")
val kotlin_version: String by extra("1.9.10")
val jacoco_version: String by extra("0.8.8")
val junit_jupiter_version: String by extra("5.6.2")
val openSearchVersion = "1.3.13"

pluginScripts {
  fromPlugin("ide")
  fromPlugin("sonarqub")
  fromPlugin("kotlin-jvm17")
  fromPlugin("kotlin")
  fromPlugin("log4j2")

  fromPlugin("junit5")
  fromPlugin("jacoco")

  fromPlugin("nexus")
  fromPlugin("publish")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")

  implementation("org.opensearch:opensearch:$openSearchVersion")
  implementation("org.opensearch.client:opensearch-rest-high-level-client:$openSearchVersion")

  testImplementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
}
