val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project

plugins {
    application
    kotlin("jvm") version "1.6.10"
    jacoco
    id("org.flywaydb.flyway") version "8.5.5"
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("io,ktor.server.netty.EnginMain")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-jackson:$ktor_version")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$ktor_version")
    implementation("org.jdbi:jdbi3-kotlin:3.27.1")
    implementation("org.jdbi:jdbi3-kotlin-sqlobject:3.27.1")
    implementation("org.jdbi:jdbi3-postgres:3.27.2")
    implementation("org.postgresql:postgresql:42.3.3")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation("io.insert-koin:koin-test:$koin_version")
    testImplementation("io.insert-koin:koin-test-junit5:$koin_version")
    testImplementation("com.h2database:h2:2.1.210")
}

tasks {
    test {
        // JUnit5を使うための設定
        useJUnitPlatform()

        testLogging {
            // テスト時の標準出力、標準エラーを出力
            showStandardStreams = true

            // テストケースごとの経過を出力
            events("passed", "skipped", "failed")

            // 例外発生時の出力設定
            setExceptionFormat("full")
        }
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

flyway {
    url = "jdbc:postgresql://localhost:5432/test"
    user = "admin"
    password = "password"
}
