package com.bumblebee.service

import com.bumblebee.model.request.CreateUserRequest
import com.bumblebee.model.response.GetLongResponse
import com.bumblebee.repository.db.UsersRepository
import com.bumblebee.util.DBTestBase
import jakarta.annotation.Resource
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import scalikejdbc.DB

class UsersSTest extends DBTestBase {

  @Resource
  val userService: UserService = null

  @Resource
  val usersRepository: UsersRepository = null

  "createNewUser" should "work as expected" in {
    val createUserRequest = CreateUserRequest(
      organizationId = 1L,
      name = "tom",
      email = "123@gmail.com",
      password = "123456",
      verified = false)
    val response: GetLongResponse = userService.createNewUser(createUserRequest)
    DB.readOnly { implicit session =>
      val maybeUsers = usersRepository.get(id = response.info.getOrElse(throw new RuntimeException("no such user")))

      maybeUsers.isDefined shouldBe true
      maybeUsers.get shouldBe maybeUsers.get.copy(
        organizationId = createUserRequest.organizationId,
        name = createUserRequest.name,
        email = createUserRequest.email,
        password = createUserRequest.password,
        verified = createUserRequest.verified
      )
    }
  }

  "getUserInfo" should "work as expected" in {
    // TODO...
  }

  "getUsers" should "work as expected" in {
    // TODO...
  }
}
