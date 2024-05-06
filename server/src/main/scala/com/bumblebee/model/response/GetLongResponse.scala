package com.bumblebee.model.response

import scala.beans.BeanProperty

case class GetLongResponse(
    @BeanProperty info: Option[Long],
    @BeanProperty error: Option[String] = None
) extends CommonNoPageGetResponse[Option[Long]]
