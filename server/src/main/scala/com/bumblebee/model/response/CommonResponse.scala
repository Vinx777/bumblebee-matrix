package com.bumblebee.model.response

import scala.beans.BeanProperty

trait CommonResponse[T] {
  @BeanProperty def error: Option[String]
  @BeanProperty def errorCode: Option[String]
}

trait CommonGetResponse[T] extends CommonResponse[T] {
  @BeanProperty def page: Option[Page]

  @BeanProperty def info: T

  @BeanProperty def error: Option[String]

  @BeanProperty def errorCode: Option[String] = None
}

trait CommonNoPageGetResponse[T] extends CommonResponse[T] {
  @BeanProperty def info: T

  @BeanProperty def error: Option[String]

  @BeanProperty def errorCode: Option[String] = None
}

case class Page(
    @BeanProperty totalCount: Long,
    @BeanProperty pageCount: Long,
    @BeanProperty pageIndex: Long,
    @BeanProperty itemsPerPage: Long
)

case class CommonEmptyResponse(
    @BeanProperty info: String = null,
    @BeanProperty error: Option[String] = None,
    @BeanProperty errorCode: Option[String] = None)
  extends CommonResponse[String]

case class UIOptionElement(
    @BeanProperty label: String,
    @BeanProperty value: String
)
