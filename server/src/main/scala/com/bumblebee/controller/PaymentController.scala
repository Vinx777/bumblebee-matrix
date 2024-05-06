package com.bumblebee.controller

import com.bumblebee.model.request.{CreatePaymentRequest, GetPaymentAmountRequest}
import com.bumblebee.model.response.{CommonEmptyResponse, GetIntResponse}
import com.bumblebee.service.PaymentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/v1/payment"))
@Tag(name = "users api")
class PaymentController(private val paymentService: PaymentService) {

  /**
    * 4. payments テーブルを期間指定で取得し、amount の合計を取得する API（参照系）
    * @param req
    * @return
    */
  @ResponseBody
  @PostMapping(path = Array("/payment/amount"))
  @Operation(summary = "get payment amount")
  def getPaymentAmount(
      @RequestBody req: GetPaymentAmountRequest
  ): ResponseEntity[GetIntResponse] =
    ResponseEntity
      .status(HttpStatus.OK)
      .body(paymentService.getPaymentAmount(req = req))

  /**
    * 5. payments テーブルにデータを登録する api を以下の条件を満たした上で作成してください（登録系）
    *   1. fee_rate は 4%、tax_rate は10% で計算し、billing_amount に計算した金額を設定する
    *        1. 例) amount が10,000の場合 billing_amount は 10,440 になる
    *   2. status は 0 で登録する
    *   3. payments 登録時に payment_bank_accounts も同時にデータを登録する
    *
    * @param req
    * @return
    */
  @ResponseBody
  @PostMapping(path = Array("/payment/create"))
  @Operation(summary = "create payment")
  def createPayment(
      @RequestBody req: CreatePaymentRequest
  ): ResponseEntity[CommonEmptyResponse] =
    ResponseEntity
      .status(HttpStatus.OK)
      .body(paymentService.createPayment(req = req))

}
