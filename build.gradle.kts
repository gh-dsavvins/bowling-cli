plugins {
    `java-library`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

group = "com.dsvv.games.bowling.cli"
version = 1.0

repositories {
    mavenCentral()
}

object ver {
    const val slf = "1.6.1"
    const val lombok = "1.18.18"
    const val junit = "4.12"
}

dependencies {
    implementation("org.slf4j:slf4j-api:${ver.slf}")
    implementation("org.slf4j:slf4j-simple:${ver.slf}")
    implementation("junit:junit:${ver.junit}")
    compileOnly("org.projectlombok:lombok:${ver.lombok}")
    annotationProcessor("org.projectlombok:lombok:${ver.lombok}")
    testCompileOnly("org.projectlombok:lombok:${ver.lombok}")
    testAnnotationProcessor("org.projectlombok:lombok:${ver.lombok}")
    testImplementation("junit:junit:${ver.junit}")
}

tasks {
    register("buildFatJar", Jar::class.java) {
        archiveClassifier.set("all")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from(configurations.runtimeClasspath.get()
                .onEach { println("add from dependencies: ${it.name}") }
                .map { if (it.isDirectory) it else zipTree(it) })
        val sourcesMain = sourceSets.main.get()
        sourcesMain.allSource.forEach { println("add from sources: ${it.name}") }
        from(sourcesMain.output)
    }
}