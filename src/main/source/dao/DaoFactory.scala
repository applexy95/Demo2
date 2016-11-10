package dao

import java.sql.{Connection, ResultSet}

/**
  * Created by ReggieYang on 2016/11/6.
  */
object DaoFactory {

  def save(conn: Connection, tableName: String, row: Array[String], columnName: Array[String]) = {
    val values = row.map(x => "?").mkString(",")
    val columns = columnName.mkString(",")
    val cmd = conn.prepareStatement(s"insert into $tableName($columns) values($values)")
    row.zipWithIndex.foreach(x => {
      cmd.setString(x._2 + 1, x._1)
    })

    cmd.addBatch()
    cmd.executeBatch()
    cmd.close()
  }

  def delete(conn: Connection, tableName: String, columns: Array[String], values: Array[String], types: Array[String]) = {
    val conditions = genConditions(columns, values, types)

    val cmd = conn.prepareStatement(s"delete from $tableName where $conditions")
    cmd.execute()
    cmd.close()
  }

  def update(conn: Connection, tableName: String, setColumns: Array[String], setValues: Array[String], setTypes: Array[String],
             whereColumns: Array[String], whereValues: Array[String], whereTypes: Array[String]) = {
    val setConditions = genConditions(setColumns, setValues, setTypes)
    val whereConditions = genConditions(whereColumns, whereValues, whereTypes)

    val cmd = conn.prepareStatement(s"update $tableName set $setConditions where $whereConditions")
    cmd.execute()
    cmd.close()
  }

  def updateByPK(conn: Connection, tableName: String, columns: Array[String], values: Array[String], types: Array[String]) = {
    val setConditions = genConditions(columns.drop(1), values.drop(1), types.drop(1), ", ")
    val whereConditions = genConditions(columns.take(1), values.take(1), types.take(1))

    val cmd = conn.prepareStatement(s"update $tableName set $setConditions where $whereConditions")
    cmd.execute()
    cmd.close()
  }

  def genConditions(columns: Array[String], values: Array[String], types: Array[String], delimeter: String = " and "): String = {
    columns.zip(values).zip(types).map(x => {
      x._1._1 + " = " + formatValue(x._1._2, x._2)
    }).mkString(delimeter)
  }

  def formatValue(value: String, columnType: String): String = {
    columnType match {
      case "string" => "\"" + value + "\""
      case _ => value
    }
  }


  def rsToObject(rs: Array[String], name: String) = {
    val x = Class.forName("model." + name)
    val u = x.getConstructor()
  }

  def select(conn: Connection, tableName: String, selectedColumns: Array[String], whereColumns: Array[String], whereValues: Array[String], whereTypes: Array[String]): Array[Array[String]] = {
    val whereConditions = genConditions(whereColumns, whereValues, whereTypes)
    val selectedColumnName = selectedColumns.mkString(",")
    val cmd = conn.prepareStatement(s"select $selectedColumnName from $tableName where $whereConditions")

    val rs = cmd.executeQuery()
    val resultSetIter = new ResultSetIter(rs, selectedColumns)
    resultSetIter.toArray
  }

  class ResultSetIter(rs: ResultSet, columns: Array[String]) extends Iterator[Array[String]] {
    override def hasNext: Boolean = rs.next()

    override def next(): Array[String] = {
      columns.map(x => rs.getString(x))
    }
  }


}
