package com.bumblebee

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.transaction.annotation.EnableTransactionManagement

import java.util.TimeZone

@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
class MainApplication

object MainApplication extends App {
  TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
  SpringApplication.run(classOf[MainApplication])
}
