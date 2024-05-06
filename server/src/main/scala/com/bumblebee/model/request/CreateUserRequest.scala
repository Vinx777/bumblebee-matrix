package com.bumblebee.model.request

import scala.beans.BeanProperty

case class CreateUserRequest(
    @BeanProperty organizationId: Long,
    @BeanProperty name: String,
    @BeanProperty email: String,
    @BeanProperty password: String,
    @BeanProperty verified: Boolean
)
