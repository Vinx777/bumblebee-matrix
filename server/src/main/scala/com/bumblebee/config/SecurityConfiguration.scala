package com.bumblebee.config

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
class SecurityConfiguration {

  @Bean
  def userDetailsService: InMemoryUserDetailsManager = {
    val user = User
      .withDefaultPasswordEncoder()
      .username("testuser")
      .password("testpw!")
      .roles("DOC")
      .build()

    new InMemoryUserDetailsManager(user)
  }

  @Bean
  def adminFilterChain(http: HttpSecurity): SecurityFilterChain = {
    http
      .authorizeHttpRequests { requests =>
        requests
          .requestMatchers(
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/v3/**")
          )
          .hasRole("DOC")
          .and()
          .httpBasic(withDefaults())
          .authorizeHttpRequests()
          .requestMatchers(
            new AntPathRequestMatcher("/auth/**"),
            new AntPathRequestMatcher("/v1/**"),
            new AntPathRequestMatcher("/actuator/**")
          )
          .anonymous()
          .and()
          .csrf()
          .disable()
          .cors()
          .disable()
      }
    http.build()
  }
}
