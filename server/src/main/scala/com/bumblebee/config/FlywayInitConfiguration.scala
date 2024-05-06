package com.bumblebee.config

import org.springframework.context.annotation.{Bean, DependsOn}
import org.springframework.stereotype.Component

@Component
class FlywayInitConfiguration {

  @DependsOn(Array("flywayInitializer"))
  @Bean(Array("flywayWaiter"))
  def flywayWaiter() = ""
}
