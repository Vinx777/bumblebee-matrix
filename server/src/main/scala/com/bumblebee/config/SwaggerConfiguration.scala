package com.bumblebee.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class SwaggerConfiguration {

  @Bean def springShopOpenAPI: OpenAPI =
    new OpenAPI()
      .info(
        new Info()
          .title("Bumblebee Matrix API")
          .description("Api to management the bumblebee matrix resources")
          .version("v1.0.0")
      )
}
