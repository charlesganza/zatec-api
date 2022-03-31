--------------------
Project Overview 
--------------------

* Uses Kotlin

* Requires a minimum of **Java version 8**

* Retrofit for networking

* Jackson JSON parser is used

* Multi-Threading is handled using **Coroutines**

------------
How to build
------------

this project uses Gradle as the build system. use /.gradlew on Unix systems or gradlew.bat on Windows systems.

to build the project, navigate to the root of the project where module level build.gradle is located /Zatec/

> **gradle bootRun** (this will run the project at :8080)

> **gradle build** (this will build a JAR for running)

you can use the the flag **--no-build-cache** to tell gradle to build from scratch.

JAR output is located at  **/project-name/libs/**
