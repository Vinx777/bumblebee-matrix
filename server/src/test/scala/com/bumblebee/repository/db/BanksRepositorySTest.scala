package com.bumblebee.repository.db

import com.bumblebee.model.entity.{Banks, BanksCreate}
import com.bumblebee.util.DBTestBase
import jakarta.annotation.Resource
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import scalikejdbc.DB

class BanksRepositorySTest extends DBTestBase {

  @Resource
  val repository: BanksRepository = null

  "BanksRepository" should "work as expected" in {
    val create = BanksCreate(bankCode = "123", bankName = "123")

    DB.localTx { implicit session =>
      val id: Long = repository.insert(create)
      val maybeBanks: Option[Banks] = repository.get(id)

      maybeBanks.isDefined shouldBe true
      maybeBanks.get shouldBe maybeBanks.get.copy(
        bankCode = create.bankCode,
        bankName = create.bankName
      )
    }
  }
}
