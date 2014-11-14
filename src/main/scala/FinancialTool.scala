import java.io.File
import java.nio.file.{Files, Paths}
import java.text.SimpleDateFormat

import scala.collection.JavaConversions._
import scala.util.Try

/** XXX
  * Created by f on 11/12/14.
  */
object FinancialTool {

  type Date = String
  type Sym = String
  type SymValue = (Sym, Double)
  type Column = (String, Double)

  case class Row(date: String, columns: Map[String, Double])

  val columnNames = Seq("Open", "High", "Low", "Close", "Volume", "AdjClose")

  def findFile(root: String, sym: Sym): Option[String] = {
    val f = new File(root + "/" + sym + ".csv")
    if (f.exists())
      Option(f.getAbsolutePath)
    else
      None
  }

  def parseFile(file: String): Seq[Row] = {
    val p = Paths.get(file)
    Files.readAllLines(p)
      .drop(1)
      .map(parseLine)
      .filter(_.isDefined)
      .flatten
  }

  def parseLine(line: String): Option[Row] = {
    line.split(",").toList match {
      case (head :: tail) if head.nonEmpty =>
        val values  = tail.flatMap(v => Try(v.toDouble).toOption)
        val columns = columnNames.zip(values).toMap
        Some(Row(head, columns))
      case _ => None
    }
  }


  def query(root: String, symbols: Set[Sym], dates: Seq[Date], col: String): Map[Date, Seq[SymValue]] = {
    val allData =
      for {
        sym <- symbols
        file <- findFile(root, sym).toList
        line <- parseFile(file)
      } yield (sym, line)

    val grouped =
      (for {
        (sym, row) <- allData
        date = row.date if dates.contains(date)
        (c, v) <- row.columns if c.toUpperCase == col.toUpperCase
      } yield (date, sym, v)).groupBy(_._1)

    grouped.map {
      case (date, values) => date -> values.map {
        case (_, s, d) => (s, d)
      }.toSeq
    }


  }



}