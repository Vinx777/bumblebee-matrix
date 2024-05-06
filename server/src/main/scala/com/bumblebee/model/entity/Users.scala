package com.bumblebee.model.entity

import java.sql.Timestamp
import scala.beans.BeanProperty

case class Users(
    @BeanProperty id: Long,
    @BeanProperty organizationId: Long,
    @BeanProperty name: String,
    @BeanProperty email: String,
    @BeanProperty password: String,
    @BeanProperty verified: Boolean,
    @BeanProperty createdAt: Timestamp,
    @BeanProperty updatedAt: Timestamp
)

case class UsersCreate(
    @BeanProperty organizationId: Long,
    @BeanProperty name: String,
    @BeanProperty email: String,
    @BeanProperty password: String,
    @BeanProperty verified: Boolean
)
