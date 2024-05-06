ThisBuild / organization := "com.bumblebee"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.17"
scalacOptions += "-Ywarn-unused"

lazy val scalaVersionShort = "2.12"
lazy val springVersion = "3.0.4"
lazy val swagger2Version = "3.0.0"
lazy val scalaTestVersion = "3.2.15"
lazy val jacksonVersion = "2.15.3"
lazy val enumerationVersion = "1.7.0"
lazy val junitVersion = "0.13.3"
lazy val springCloudVersion = "4.0.2"

lazy val util = (project in file("util"))
  .settings(
    version := "1.0.0",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % scalaTestVersion
    ).map(_ % Test),
    libraryDependencies ++= Seq(
      // sttp
      "com.softwaremill.sttp.client3" %% "core" % "3.8.15",
      "com.softwaremill.sttp.client3" %% "fs2" % "3.8.15",
      // scalikejdbc
      "org.scalikejdbc" %% "scalikejdbc" % "4.0.0",
      "org.scala-lang" % "scala-compiler" % "2.12.17",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
      "org.slf4j" % "slf4j-api" % "2.0.5"
    )
  )

lazy val server = (project in file("server"))
  .enablePlugins(JavaAppPackaging)
  .dependsOn(util)
  .settings(
    Test / parallelExecution := false,
    Compile / mainClass := Some("com.bumblebee.MainApplication"),
    //    Runtime / managedClasspath += (Assets / packageBin).value,
    libraryDependencies ++= Seq(

      "org.springframework.boot" % "spring-boot-starter-test" % springVersion,
      "org.scalatest" %% "scalatest" % scalaTestVersion,
      "com.github.sbt" % "junit-interface" % junitVersion,
      "org.scalatestplus" %% "mockito-4-5" % "3.2.12.0",
      "com.h2database" % "h2" % "2.1.214",
    ).map(_ % Test),
    libraryDependencies ++= Seq(
      //      "org.springframework.boot" % "spring-boot-devtools" % springVersion,
      "org.springframework.boot" % "spring-boot-starter-actuator" % springVersion,
      "org.springframework.boot" % "spring-boot-starter-web" % springVersion,
      // jwt
      "io.jsonwebtoken" % "jjwt-api" % "0.11.5",
      "io.jsonwebtoken" % "jjwt-impl" % "0.11.5",
      "io.jsonwebtoken" % "jjwt-jackson" % "0.11.5",
      // logging
      "org.springframework.boot" % "spring-boot-starter-log4j2" % springVersion,
      //"org.apache.logging.log4j" % "log4j-slf4j18-impl" % "2.18.0",
      // metrics
      "io.micrometer" % "micrometer-registry-statsd" % "1.10.5",
      "io.micrometer" % "micrometer-registry-prometheus" % "1.10.5",
      "org.springframework" % "spring-aspects" % "6.0.10",
      // security
      "org.springframework.boot" % "spring-boot-starter-security" % springVersion,
      "org.springframework.boot" % "spring-boot-starter-oauth2-resource-server" % springVersion,
      // DB
      "org.springframework.boot" % "spring-boot-starter-data-jdbc" % springVersion,
      "mysql" % "mysql-connector-java" % "8.0.33",
      // flyway
      "org.flywaydb" % "flyway-core" % "9.16.0",
      "org.flywaydb" % "flyway-community-db-support" % "9.16.0",
      // model
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
      //swagger
      // "io.springfox" % "springfox-boot-starter" % swagger2Version,
      "org.springdoc" % "springdoc-openapi-starter-webmvc-ui" % "2.0.2",
      ("com.github.swagger-akka-http" %% "swagger-scala-module" % "2.9.0")
        .excludeAll(ExclusionRule(organization = "org.slf4j")),
      "javax.validation" % "validation-api" % "2.0.1.Final",
      // lock
      "net.javacrumbs.shedlock" % "shedlock-spring" % "5.2.0",
      "net.javacrumbs.shedlock" % "shedlock-provider-jdbc-template" % "5.2.0",
      //"org.slf4j" % "slf4j-api" % "2.0.3"
      // etcd
      //("io.etcd" % "jetcd-core" % "0.7.5").excludeAll(ExclusionRule(organization = "org.slf4j"))
      // redis
      "org.springframework.boot" % "spring-boot-starter-data-redis" % springVersion,
      "org.apache.commons" % "commons-pool2" % "2.11.1",
      // image processing
      "com.twelvemonkeys.imageio" % "imageio-core" % "3.9.4",
      "org.imgscalr" % "imgscalr-lib" % "4.2",
      // stream
      "org.springframework.cloud" % "spring-cloud-stream" % springCloudVersion,
      "org.springframework.cloud" % "spring-cloud-starter-stream-kafka" % springCloudVersion,
      "org.sejda.imageio" % "webp-imageio" % "0.1.6"
    ),
    excludeDependencies ++= Seq(
      "org.springframework.boot" % "spring-boot-starter-logging",
      //      "org.apache.logging.log4j" % "log4j-slf4j-impl",
    )
  )
