package dao

import model.{Followup, Project, Risk, User}
import util.Utils

/**
  * Created by ReggieYang on 2016/11/6.
  */
object ModelFactory {

  def createUser(user: Array[String]): User = {
    User(user(0), user(1), user(2), user(3))
  }

  def createProject(project: Array[String]): Project = {
    Project(project(0).toInt, project(1), project(2))
  }

  def createFollowup(followup: Array[String]): Followup = {
    Followup(followup(0).toInt, followup(1), followup(2), followup(3).toInt)
  }

  def createRisk(risk: Array[String]): Risk = {
    Risk(risk(0).toInt, risk(1), risk(2), risk(3), risk(4), risk(5), risk(6), risk(7), risk(8).toInt)
  }

  def getTableName(cls: Class[_]): String = cls.getSimpleName.toLowerCase

  def getFieldNames(cls: Class[_]): Array[String] = cls.getDeclaredFields.map(field => Utils.toSnack(field.getName))

  def getFieldTypes(cls: Class[_]): Array[String] = cls.getDeclaredFields.map(field => Utils.getType(field.getType.getSimpleName))

}
