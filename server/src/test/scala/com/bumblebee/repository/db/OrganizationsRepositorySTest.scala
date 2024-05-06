package com.bumblebee.repository.db

import com.bumblebee.model.entity.{Organizations, OrganizationsCreate, OrganizationsUpdate}
import com.bumblebee.util.DBTestBase
import jakarta.annotation.Resource
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import scalikejdbc.DB

class OrganizationsRepositorySTest extends DBTestBase {

  @Resource
  val repository: OrganizationsRepository = null

  "OrganizationsRepository" should "work as expected" in {
    val create = OrganizationsCreate(
      name = "name",
      representativeName = "test",
      phoneNumber = "test",
      postalCode = "test",
      address = "test"
    )

    val update = OrganizationsUpdate(
      name = Some("name2")
    )

    DB.localTx { implicit session =>
      val id: Long = repository.insert(create)
      val maybeOrganizations: Option[Organizations] = repository.get(id)

      maybeOrganizations.isDefined shouldBe true
      maybeOrganizations.get shouldBe maybeOrganizations.get.copy(
        name = create.name,
        representativeName = create.representativeName,
        phoneNumber = create.phoneNumber,
        postalCode = create.postalCode,
        address = create.address
      )

      repository.update(id, update)
      val maybeOrganizations2: Option[Organizations] = repository.get(id)
      maybeOrganizations2.isDefined shouldBe true
      maybeOrganizations2.get shouldBe maybeOrganizations2.get.copy(
        name = update.name.get
      )
    }
  }
}
