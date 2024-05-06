package com.bumblebee.model.response

import com.bumblebee.model.entity.Users

import scala.beans.BeanProperty

case class GetUserInfoResponse(
    @BeanProperty info: Option[Users],
    @BeanProperty error: Option[String] = None
) extends CommonNoPageGetResponse[Option[Users]]
