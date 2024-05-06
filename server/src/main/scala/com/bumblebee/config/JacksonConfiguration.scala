package com.bumblebee.config

import com.bumblebee.util.{CommonLogging, JsonUtil}
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.{Bean, Configuration, Primary}

@Configuration
class JacksonConfiguration extends CommonLogging {
  @Bean
  @Primary
  def objectMapper(): ObjectMapper =
    JsonUtil.getMapper
}
