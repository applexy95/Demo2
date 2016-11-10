package util

import java.io.StringWriter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
  * Created by ReggieYang on 2016/11/7.
  */
object Utils {

  lazy val EmptyString = ""

  def toCamel(snack: String): String = {
    val terms = snack.toLowerCase().split("_")
    terms.reduceLeft(
      (acc: String, term: String) => acc + (term.head.toUpper + term.tail)
    )
  }

  def toSnack(camel: String): String = {
    val startWithLow = camel.head.toLower + camel.tail
    startWithLow.foldLeft("")((acc, c) => if (c.isUpper) acc + "_" + c.toLower else acc + c)
  }

  def getType(typeName: String) = {
    typeName match {
      case "int" => "int"
      case "double" => "double"
      case _ => "string"
    }
  }

  lazy val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  def serializeJson(o: Any): String = {
    val out = new StringWriter()
    mapper.writeValue(out, o)
    out.toString
  }

  def deserializeJson[T](json: String, cls: Class[T]) = mapper.readValue(json, cls)

  def object2Array(obj: Any): Array[String] = {
    obj.getClass.getDeclaredFields.map(x => {
      x.setAccessible(true)
      x.get(obj).toString
    })
  }

  def deserializeJson2Array[T](json: String, cls: Class[T]): Array[String] = object2Array(deserializeJson(json, cls))


  def deserializeJsonArray(json: String): Array[String] = mapper.readValue(json, classOf[Array[String]])

}
