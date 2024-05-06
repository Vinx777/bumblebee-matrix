package com.bumblebee.model.entity

import java.sql.Timestamp
import scala.beans.BeanProperty

case class Organizations(
    @BeanProperty id: Long,
    @BeanProperty name: String,
    @BeanProperty representativeName: String,
    @BeanProperty phoneNumber: String,
    @BeanProperty postalCode: String,
    @BeanProperty address: String,
    @BeanProperty createdAt: Timestamp,
    @BeanProperty updatedAt: Timestamp
)

case class OrganizationsCreate(
    @BeanProperty name: String,
    @BeanProperty representativeName: String,
    @BeanProperty phoneNumber: String,
    @BeanProperty postalCode: String,
    @BeanProperty address: String
)

case class OrganizationsUpdate(
    @BeanProperty name: Option[String] = None,
    @BeanProperty representativeName: Option[String] = None,
    @BeanProperty phoneNumber: Option[String] = None,
    @BeanProperty postalCode: Option[String] = None,
    @BeanProperty address: Option[String] = None
)
