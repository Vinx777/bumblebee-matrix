package com.bumblebee.util

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.core.StreamReadConstraints
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.{DeserializationFeature, JsonNode, ObjectMapper, PropertyNamingStrategies}
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scala.collection.mutable
import scala.language.postfixOps
import scala.reflect.ClassTag

object JsonUtil {

  private lazy val mapper: ObjectMapper = {
    // workaround of too big problem
    StreamReadConstraints.overrideDefaultStreamReadConstraints(
      StreamReadConstraints
        .builder()
        .maxNestingDepth(1000)
        .maxNumberLength(1000)
        .maxStringLength(100 * 1000 * 1000)
        .build()
    )

    JsonMapper
      .builder()
      .addModule(DefaultScalaModule)
      .build()
      .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .setSerializationInclusion(Include.NON_ABSENT)
  }

  def toMap(value: String): Map[String, String] =
    mapper.readValue(value, classOf[Map[String, String]])

  def toMutableMap(value: String): mutable.Map[String, String] =
    mapper.readValue(value, classOf[mutable.Map[String, String]])

  def toJSON(value: Object): String =
    mapper.writeValueAsString(value)

  def fromJSON[T](json: String)(implicit tag: ClassTag[T]): T =
    if (tag.runtimeClass == classOf[String]) {
      json.asInstanceOf[T]
    }
    else {
      mapper.readValue(json, tag.runtimeClass).asInstanceOf[T]
    }

  def getMapper: ObjectMapper = mapper

  def toJSONObject(value: String): JsonNode =
    mapper.readTree(value)

  def toJSONObject(value: Object): JsonNode =
    mapper.valueToTree(value)

  def toObjectNode(value: String): ObjectNode =
    mapper.readTree(value).asInstanceOf[ObjectNode]

  def toObjectNode(value: Object): ObjectNode =
    mapper.valueToTree(value).asInstanceOf[ObjectNode]

  def fromJsonObject[T](value: JsonNode)(implicit tag: ClassTag[T]): T =
    if (tag.runtimeClass == classOf[String]) {
      value.toPrettyString.asInstanceOf[T]
    }
    else {
      mapper.treeToValue(value, tag.runtimeClass).asInstanceOf[T]
    }
}
