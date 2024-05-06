package com.bumblebee.repository.db

import com.bumblebee.model.entity.{Users, UsersCreate}
import com.bumblebee.util.DBTestBase
import jakarta.annotation.Resource
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import scalikejdbc.DB

class UsersRepositorySTest extends DBTestBase {

  @Resource
  val repository: UsersRepository = null

  "UsersRepository" should "work as expected" in {
    val create =
      UsersCreate(organizationId = 1L, name = "name", email = "email", password = "password", verified = false)

    DB.localTx { implicit session =>
      val id: Long = repository.insert(create)
      val maybeUsers: Option[Users] = repository.get(id)

      maybeUsers.isDefined shouldBe true
      maybeUsers.get shouldBe maybeUsers.get.copy(
        organizationId = create.organizationId,
        name = create.name,
        email = create.email,
        password = create.password,
        verified = create.verified
      )
    }
  }
}
