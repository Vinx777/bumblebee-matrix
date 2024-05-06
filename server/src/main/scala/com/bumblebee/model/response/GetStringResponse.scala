package com.bumblebee.model.response

import scala.beans.BeanProperty

case class GetStringResponse(
    @BeanProperty info: Option[String],
    @BeanProperty error: Option[String] = None
) extends CommonNoPageGetResponse[Option[String]]
