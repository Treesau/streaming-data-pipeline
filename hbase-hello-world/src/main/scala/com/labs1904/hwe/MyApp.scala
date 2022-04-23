package com.labs1904.hwe

import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Delete, Get, Put, ResultScanner, Scan}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.logging.log4j.{LogManager, Logger}

object MyApp {
  lazy val logger: Logger = LogManager.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    logger.info("MyApp starting...")
    var connection: Connection = null
    try {
      val conf = HBaseConfiguration.create()
      //conf.set("hbase.zookeeper.quorum", "CHANGE ME")
      conf.set("hbase.zookeeper.quorum", "hbase02.hourswith.expert:2181")
      connection = ConnectionFactory.createConnection(conf)
      // Example code... change me
      val table = connection.getTable(TableName.valueOf("tsauer:users"))
      val get = new Get(Bytes.toBytes("row-key"))
      val result = table.get(get)
      logger.debug(result)

      // Challenge 1
      val c1Get = new Get(Bytes.toBytes("10000001"))
      c1Get.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("mail"))
      val c1Result = table.get(c1Get)
      val emailAddress = Bytes.toString(c1Result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("mail")))
      logger.debug(emailAddress)

      // Challenge 2
      val put = new Put(Bytes.toBytes("99"))
      put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes("The Panther"))
      put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("username"), Bytes.toBytes("DE-HWE"))
      put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("sex"), Bytes.toBytes("F"))
      put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("favorite_color"), Bytes.toBytes("pink"))
      table.put(put)

      val c2Get = new Get(Bytes.toBytes("99"))
      c2Get.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"))
      c2Get.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("username"))
      c2Get.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("sex"))
      val c2Result = table.get(c2Get)
      val c2Name = Bytes.toString(c2Result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("name")))
      val c2Username = Bytes.toString(c2Result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("username")))
      val c2Sex = Bytes.toString(c2Result.getValue(Bytes.toBytes("f1"), Bytes.toBytes("sex")))
      logger.debug(c2Name + " " + c2Username + " " + c2Sex)

      // Challenge 3
      val c3Scan = new Scan()
      c3Scan.withStartRow(Bytes.toBytes("10000001"))
      c3Scan.withStopRow(Bytes.toBytes("10006001"))
      val scanner = table.getScanner(c3Scan).iterator();
      var count = 0
      while(scanner.hasNext) {
        val c3Result = scanner.next()
        count = count + 1
      }
      println("count: " + count)

      // Challenge 4
      val delete = new Delete(Bytes.toBytes("99"))

    } catch {
      case e: Exception => logger.error("Error in main", e)
    } finally {
      if (connection != null) connection.close()
    }
  }
}
