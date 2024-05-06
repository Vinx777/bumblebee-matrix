package com.bumblebee.repository.db

import com.bumblebee.model.entity.{Organizations, OrganizationsCreate, OrganizationsUpdate}
import org.springframework.stereotype.Component
import scalikejdbc.{DBSession, SQLSyntax, SQLUtil, WrappedResultSet, scalikejdbcSQLInterpolationImplicitDef}

@Component
class OrganizationsRepository {

  def insert(entity: OrganizationsCreate)(implicit session: DBSession): Long = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | INSERT INTO
            |   organizations (
            |     name,
            |     representative_name,
            |     phone_number,
            |     postal_code,
            |     address
            |   ) VALUES (
            |     ${entity.name},
            |     ${entity.representativeName},
            |     ${entity.phoneNumber},
            |     ${entity.postalCode},
            |     ${entity.address}
            |   )
            |""".stripMargin
    SQLUtil.sql(sqlQuery).updateAndReturnGeneratedKey(1).apply
  }

  def get(id: Long)(implicit session: DBSession): Option[Organizations] = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | SELECT
            |   *
            | FROM organizations
            | WHERE id = $id
            |""".stripMargin
    SQLUtil
      .sql(sqlQuery)
      .map(mapEntity)
      .single
      .apply()
  }

  def update(id: Long, entity: OrganizationsUpdate)(implicit session: DBSession): Long = {
    val sqlQuery: SQLSyntax =
      sqls"""
            | UPDATE
            |   organizations
            | SET
            |   name = COALESCE(${entity.name}, name),
            |   representative_name = COALESCE(${entity.representativeName}, representative_name),
            |   phone_number = COALESCE(${entity.phoneNumber}, phone_number),
            |   postal_code = COALESCE(${entity.postalCode}, postal_code),
            |   address = COALESCE(${entity.address}, address)
            | WHERE id = $id
            |""".stripMargin
    SQLUtil.sql(sqlQuery).update.apply.toLong
  }

  def mapEntity(ws: WrappedResultSet): Organizations =
    Organizations(
      id = ws.long("id"),
      name = ws.string("name"),
      representativeName = ws.string("representative_name"),
      phoneNumber = ws.string("phone_number"),
      postalCode = ws.string("postal_code"),
      address = ws.string("address"),
      createdAt = ws.timestamp("created_at"),
      updatedAt = ws.timestamp("updated_at")
    )
}
