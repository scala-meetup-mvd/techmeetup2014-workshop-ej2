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

  case class Row(date: String, columns: Map[String, Double])

  type Sym      = String
  type SymValue = (Sym, Double)
  type Column   = (String, Double)


  val columnNames = Set("Open","High","Low","Close","Volume", "AdjClose")

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
    Row( head, columns )
  }



  def query(root: String, symbols: Set[Sym], dates: Seq[String], col: String): Map [String, Seq[SymValue]] = {

    val allData =
        for {
          sym   <-  symbols
          file  <- findFile(root, sym).toList
          line  <- readLines(file)
        } yield (sym, parseLine(line))

    val grouped =
      (for {
        (sym,row) <- allData
        date = row.date if dates.contains(date)
        (c,v) <- row.columns if c.toUpperCase == col.toUpperCase
      } yield (date, sym, v)).groupBy(_._1)

    grouped.map {
      case (d,values) => d -> values.map {
        case (_,s,d) => (s,d)
      }.toSeq
    }


  }

   def dateFromString(date: String) = {
    val simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD")
    simpleDateFormat.parse(date)
  }



}