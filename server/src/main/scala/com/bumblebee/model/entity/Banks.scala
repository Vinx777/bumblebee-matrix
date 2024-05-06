package com.bumblebee.model.entity

import java.sql.Timestamp
import scala.beans.BeanProperty

case class Banks(
    @BeanProperty id: Long,
    @BeanProperty bankCode: String,
    @BeanProperty bankName: String,
    @BeanProperty createdAt: Timestamp,
    @BeanProperty updatedAt: Timestamp
)

case class BanksCreate(
    @BeanProperty bankCode: String,
    @BeanProperty bankName: String
)
