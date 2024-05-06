package com.bumblebee.service

import com.bumblebee.repository.db.UsersRepository
import com.bumblebee.util.DBTestBase
import jakarta.annotation.Resource

class PaymentsSTest extends DBTestBase {

  @Resource
  val userService: UserService = null

  @Resource
  val usersRepository: UsersRepository = null

  "createPayment" should "work as expected" in {
    // TODO...
  }

  "getPaymentAmount" should "work as expected" in {
    // TODO...
  }
}
