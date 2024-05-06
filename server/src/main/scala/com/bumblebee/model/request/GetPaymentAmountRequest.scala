package com.bumblebee.model.request

import java.sql.Date
import scala.beans.BeanProperty

case class GetPaymentAmountRequest(
    @BeanProperty startTransferDate: Date,
    @BeanProperty endTransferDate: Date
)
