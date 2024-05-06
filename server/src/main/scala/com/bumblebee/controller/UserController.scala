package com.bumblebee.controller

import com.bumblebee.model.request.CreateUserRequest
import com.bumblebee.model.response.{GetLongResponse, GetUserInfoResponse, GetUsersPageResponse}
import com.bumblebee.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/v1/user"))
@Tag(name = "users api")
class UserController(private val userService: UserService) {

  /**
   * 3.users テーブルの情報を指定した件数分、かつ、ページングを考慮して取得する API（参照系）
   * @param pageIndex
   * @param itemsPerPage
   * @return
   */
  @ResponseBody
  @GetMapping(path = Array(""))
  @Operation(summary = "get users")
  def getUsers(
      @RequestParam("page_index") pageIndex: Long,
      @RequestParam("items_per_page") itemsPerPage: Long
  ): ResponseEntity[GetUsersPageResponse] =
    ResponseEntity
      .status(HttpStatus.OK)
      .body(
        userService
          .getUsers(pageIndex, itemsPerPage))

  /**
   * 1. user_id を指定して users テーブルから取得する API（参照系）
   * @param userId
   * @return
   */
  @ResponseBody
  @GetMapping(path = Array("/{user_id}"))
  @Operation(summary = "get user info by id")
  def getUserInfo(
      @PathVariable(value = "user_id", required = true) userId: String
  ): ResponseEntity[GetUserInfoResponse] =
    ResponseEntity
      .status(HttpStatus.OK)
      .body(
        userService
          .getUserInfo(userId = userId.toLong))

  /**
   * 2. user の情報を送信して users テーブルに登録する API（登録系）
   * @param req
   * @return
   */
  @ResponseBody
  @PostMapping(path = Array(""))
  @Operation(summary = "add new user")
  def createNewUser(
      @RequestBody req: CreateUserRequest
  ): ResponseEntity[GetLongResponse] =
    ResponseEntity
      .status(HttpStatus.OK)
      .body(userService.createNewUser(req))
}
