/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

plugins {
  val kotlinVersion = "1.9.22"
  id("org.jetbrains.kotlin.jvm") version (kotlinVersion)
  id("org.jetbrains.kotlin.plugin.noarg") version (kotlinVersion)
  id("net.semandex.plugins.gradle-scripts-plugin") version "0.5.2"
}

description = "eskotlin project"
group = "mbuhot"
version = "2.1.0"

val log4jVersion: String by extra("2.23.1")
val kotlinVersion: String by extra("1.9.22")
val jacocoVersion: String by extra("0.8.11")
val junitJupiterVersion: String by extra("5.6.2")
val openSearchVersion = "2.13.0"

pluginScripts {
  fromPlugin("ide")
  fromPlugin("kotlin-jvm17")
  fromPlugin("log4j2")

  fromPlugin("junit5")
  fromPlugin("jacoco")

  fromPlugin("nexus")
  fromPlugin("publish")
}

//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//  kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
//}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

  implementation("org.opensearch:opensearch:$openSearchVersion")
  implementation("org.opensearch.client:opensearch-rest-high-level-client:$openSearchVersion")

  testImplementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
}
