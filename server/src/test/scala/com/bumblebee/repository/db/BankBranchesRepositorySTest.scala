package com.bumblebee.repository.db

import com.bumblebee.model.entity.{BankBranches, BankBranchesCreate}
import com.bumblebee.util.DBTestBase
import jakarta.annotation.Resource
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import scalikejdbc.DB

class BankBranchesRepositorySTest extends DBTestBase {

  @Resource
  val repository: BankBranchesRepository = null

  "BankBranchesRepository" should "work as expected" in {
    val create = BankBranchesCreate(bankCode = "123", branchCode = "123", branchName = "123")

    DB.localTx { implicit session =>
      val id: Long = repository.insert(create)
      val maybeBankBranches: Option[BankBranches] = repository.get(id)

      maybeBankBranches.isDefined shouldBe true
      maybeBankBranches.get shouldBe maybeBankBranches.get.copy(
        bankCode = create.bankCode,
        branchCode = create.branchCode,
        branchName = create.branchName
      )
    }
  }
}
