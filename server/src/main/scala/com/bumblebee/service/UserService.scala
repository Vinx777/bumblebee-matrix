package com.bumblebee.service

import com.bumblebee.model.entity.{Users, UsersCreate}
import com.bumblebee.model.request.CreateUserRequest
import com.bumblebee.model.response.{GetLongResponse, GetUserInfoResponse, GetUsersPageResponse, Page}
import com.bumblebee.repository.db._
import com.bumblebee.util.CommonLogging
import org.springframework.stereotype.Component
import scalikejdbc.DB

@Component
class UserService(
    private val usersRepository: UsersRepository
) extends CommonLogging {

  def getUsers(pageIndex: Long, rowPerPage: Long): GetUsersPageResponse = DB.readOnly { implicit session =>
    val users: List[Users] = usersRepository
      .getResult(pageIndex = pageIndex, rowPerPage = rowPerPage)
    val rowCount: Long =
      usersRepository.getRowCount()

    GetUsersPageResponse(
      info = users,
      error = None,
      page = Some(
        Page(
          totalCount = rowCount,
          pageCount = rowCount / rowPerPage,
          pageIndex = pageIndex,
          itemsPerPage = rowPerPage))
    )
  }

  def getUserInfo(userId: Long): GetUserInfoResponse =
    DB.readOnly { implicit session =>
      val maybeUsers: Option[Users] = usersRepository.get(id = userId)

      GetUserInfoResponse(info = maybeUsers, error = None)
    }

  def createNewUser(req: CreateUserRequest): GetLongResponse = {
    val userId: Long = DB.localTx { implicit session =>
      usersRepository.insert(
        UsersCreate(
          organizationId = req.organizationId,
          name = req.name,
          email = req.email,
          password = req.password,
          verified = req.verified
        ))
    }

    GetLongResponse(info = Some(userId), error = None)
  }
}
