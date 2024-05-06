package com.bumblebee.model.response

import com.bumblebee.model.entity.Users

import scala.beans.BeanProperty

case class GetUsersPageResponse(
    @BeanProperty info: List[Users],
    @BeanProperty error: Option[String] = None,
    @BeanProperty page: Option[Page]
) extends CommonGetResponse[List[Users]]
