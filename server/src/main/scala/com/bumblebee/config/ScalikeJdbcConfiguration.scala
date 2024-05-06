package com.bumblebee.config

import com.bumblebee.annotation.{NotTest, OnlyTest}
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.{Bean, Configuration}
import scalikejdbc.{ConnectionPool, DataSourceConnectionPool, GlobalSettings, LoggingSQLAndTimeSettings}

import javax.sql.DataSource

@Configuration
class ScalikeJdbcConfiguration(private val dataSource: DataSource) {

  @Bean(name = Array("connectionPoolFlag"))
  def initializeConnectionPool(): Int = {
    ConnectionPool.singleton(new DataSourceConnectionPool(dataSource))
    1
  }

  @OnlyTest
  @PostConstruct
  def initConfigurationTest(): Unit =
    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = true,
      singleLineMode = true,
      printUnprocessedStackTrace = false,
      logLevel = "debug"
    )

  @NotTest
  @PostConstruct
  def initConfiguration(): Unit =
    GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
      enabled = false,
      singleLineMode = true,
      printUnprocessedStackTrace = false,
      logLevel = "warn"
    )
}
