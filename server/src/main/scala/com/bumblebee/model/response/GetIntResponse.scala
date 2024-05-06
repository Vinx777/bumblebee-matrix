package com.bumblebee.model.response

import scala.beans.BeanProperty

case class GetIntResponse(
    @BeanProperty info: Option[Int],
    @BeanProperty error: Option[String] = None
) extends CommonNoPageGetResponse[Option[Int]]
