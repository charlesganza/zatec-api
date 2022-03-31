import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
}

group = "zatec"
version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	//spring core
	implementation("org.springframework.boot:spring-boot-starter-web:2.6.5")
	implementation("org.springframework.boot:spring-boot-starter-security:2.6.5")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	//kotlin
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	//coroutines
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:1.6.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.0")
	//reactive streams
	implementation("org.reactivestreams:reactive-streams:1.0.3")
	//h2 database
	implementation("com.h2database:h2:2.1.210")
	//json parser Jackson
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.+")
	//http client Retrofit
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-jackson:2.9.0")
	testImplementation("com.squareup.okhttp3:mockwebserver:4.9.0")
	//junit
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

	implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("spring-security-starter.jar"))))
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
