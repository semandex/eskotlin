/*
 * Copyright (c) 2016. Michael Buhot m.buhot@gmail.com
 */

//buildscript {
//    repositories {
//        mavenCentral()
//        jcenter()
//    }
//    dependencies {
//        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61"
//        classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.+'
//    }
//}

plugins {
  id("java-base")
  id("org.jetbrains.kotlin.jvm") version "1.6.10"
  id("org.jetbrains.kotlin.plugin.noarg") version "1.6.10"
//  id("org.jetbrains.kotlin.jvm") version "1.9.10"
//  id("org.jetbrains.kotlin.plugin.noarg") version "1.9.10"

  id("net.semandex.plugins.gradle-scripts-plugin") version "0.4.6"
}

description = "eskotlin project"
group "mbuhot"
version "1.1.0beta1"

//apply plugin: 'java-base'
//apply plugin: 'kotlin'

val log4jVersion: String by extra("2.20.0")
//val kotlin_version: String by extra("1.6.10")
val kotlin_version: String by extra("1.3.61")
////val kotest_version: String by extra("5.7.2")
//val kotest_version: String by extra("5.3.0")
////val mockkVersion: String by extra("1.13.8")
//val mockkVersion: String by extra("1.12.4")
//val jacoco_version: String by extra("0.8.8")
val junit_jupiter_version: String by extra("5.6.2")
val openSearchVersion = "1.3.13"

pluginScripts {
  fromPlugin("ide")
////  fromPlugin("sonarqub")
  fromPlugin("kotlin-jvm17")
  fromPlugin("kotlin")
  fromPlugin("log4j2")

  fromPlugin("junit5")
//  fromPlugin("jacoco")

  fromPlugin("nexus")
  fromPlugin("publish")
}

dependencies {
    //compile "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
  implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")

//    compile "org.opensearch:opensearch:$openSearchVersion"
//    compile "org.opensearch.plugin:parent-join-client:$openSearchVersion"

  implementation("org.opensearch:opensearch:$openSearchVersion")
  implementation("org.opensearch.client:opensearch-rest-high-level-client:$openSearchVersion")

  testImplementation("org.apache.logging.log4j:log4j-api:2.20.0")
  testImplementation("org.apache.logging.log4j:log4j-core:2.20.0")
  testImplementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
//  testImplementation("junit:junit:4.13.2")
}
