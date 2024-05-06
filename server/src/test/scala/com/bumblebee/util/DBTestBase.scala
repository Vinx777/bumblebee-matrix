package com.bumblebee.util

import com.bumblebee.MainApplication
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach}
import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.{ActiveProfiles, TestContextManager}
import scalikejdbc.{DB, SQL}

@RunWith(classOf[SpringRunner])
@ActiveProfiles(profiles = Array("test"))
@ExtendWith(value = Array(classOf[SpringExtension]))
@SpringBootTest(classes = Array(classOf[MainApplication]))
class DBTestBase
  extends AnyFlatSpec with BeforeAndAfterAll with BeforeAndAfterEach with EnvironmentPostProcessor with CommonLogging {

  override def beforeAll(): Unit =
    new TestContextManager(this.getClass).prepareTestInstance(this)

  override def beforeEach(): Unit = {
    logger.info("cleaning all tables")
    Seq(
      "organizations",
      "users",
      "payments",
      "payment_bank_accounts",
      "banks",
      "bank_branches"
    ).foreach(truncateTable)
  }

  def truncateTable(name: String): Unit =
    DB.localTx { implicit session =>
      SQL(s"""
           | DELETE FROM $name
           |""".stripMargin).execute.apply
    }

  override def postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication): Unit =
    environment.setActiveProfiles("test")
}
