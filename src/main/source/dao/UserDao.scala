package dao

import java.sql.Connection

import model.User

/**
  * Created by ReggieYang on 2016/11/6.
  */
class UserDao(conn: Connection) {

  val userTable = ModelFactory.getTableName(classOf[User])
//  val columns = Array("user_name", "position", "password", "company")
//  val types = Array("string", "string", "string", "string")
  val userColumns = ModelFactory.getFieldNames(classOf[User])
  val userTypes = ModelFactory.getFieldTypes(classOf[User])
  val pk = userColumns.take(1)
  val pkTypes = userTypes.take(1)

  def getUser(userName: String): User = {
    val users = DaoFactory.select(conn, userTable, userColumns, pk, Array(userName), pkTypes)
    if (users.length == 0) null
    else ModelFactory.createUser(users(0))
  }

  def getColleague(company: String): Array[User] = {
    DaoFactory.select(conn, userTable, userColumns, userColumns.takeRight(1), Array(company), userTypes.takeRight(1)).map(x => ModelFactory.createUser(x))
  }

}
