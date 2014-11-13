import java.io.File
import java.nio.file.{Files, Paths}
import java.text.SimpleDateFormat
import java.util.Date

import scala.collection.JavaConversions._
import scala.util.Try

/** XXX
  * Created by f on 11/12/14.
  */
object FinancialTool {

  case class Row(date: Date, columns: Map[String, Double])

  type Sym      = String
  type SymValue = (Sym, Double)

  val columnNames = Seq("Open","High","Low","Close","Volume", "AdjClose")

  def findFile(root: String, sym: Sym): Option[String]  = {
      val f = new File(root+"/"+sym+".csv")
      if (f.exists())
        Option(f.getAbsolutePath)
      else
        None
  }

  def readLines(file: String): Seq[String] = {
    val p = Paths.get(file)
    Files.readAllLines(p)
      .drop(1)
      .filter( ! _.isEmpty )
  }

  def parseLine(line: String): Row  = {
    val (head :: tail) = line.split(",").toList
    val values = tail.map( v => Try(v.toDouble).getOrElse(0D) )
    val columns = columnNames.zip(values).toMap
    Row( dateFromString(head), columns )
  }



  def query(root: String, symbols: Set[Sym], dates: Seq[Date], col: String): Map [Date, Seq[SymValue]] = {
//    val allData = symbols.map { sym =>
//      val rows =
//        for {
//          file  <- findFiles(root, sym)
//          line  <- readLines(file)
//        } yield parseLine(line)
//      (sym, rows)
//    }.toMap
//    val x = for {
//      (k,v) <- allData
//      //rows <- v.toMap[]
//    }
    ???
  }


  private def dateFromString(date: String) = {
    val simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD")
    simpleDateFormat.parse(date)
  }



}