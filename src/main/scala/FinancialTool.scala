import java.text.SimpleDateFormat
import java.util.Date
import java.io.File

import scala.io.Source
import scala.util.Try

/** XXX
 * Created by f on 11/12/14.
 */
object FinancialTool {

  type Date     = String
  type Sym      = String
  type SymValue = (Sym, Double)
  type Column   = (String, Double)

  case class Row(date: Date, columns: Map[String, Double])

  val columnNames = Seq("Open","High","Low","Close","Volume", "AdjClose")


  def findFile(root: String, symbol: Sym): Option[String] = {
    val file = new File(root, s"$symbol.csv")
    
    if (file.exists) Some(file.getCanonicalPath) else None
  }

  val DateRegex = """(\d\d\d\d-\d\d-\d\d)""".r

  def parseLine(line:String): Option[Row]  = {
    val parts = line.split(",")
    val optDate = parts.lift(0)

    for {
      DateRegex(date) <- optDate
    } yield {
      val columnParts = parts.tail.lift

      val validCols =
        for {
          (col, i) <- columnNames.zipWithIndex
          partStr <- columnParts(i)
          tryPart = Try(partStr.toDouble)
          part <- tryPart.toOption
        } yield {
          col -> part
        }
      
      Row(date, validCols.toMap)
      
    }
  }

  def allLines(file: String):  Seq[String] = Source.fromFile(file).getLines.toStream 

  def parseFile(file: String):  Seq[Row] = allLines(file).flatMap(parseLine)

  def query(root: String, symbols: Set[Sym], dates: Seq[Date], col: String): Map [Date, Seq[SymValue]] = {
    val symRows =  
      symbols
        .flatMap(sym => findFile(root, sym).map(sym -> _))
        .toMap
        .mapValues(parseFile)

    val res =
      for {
        date <- dates
      } yield {
        val dateSymRows = symRows.mapValues(_.filter(_.date == date))

        val values =
          for {
            (sym, rows) <- dateSymRows.toSeq
            value <- rows.flatMap(_.columns.get(col))
          } yield {
            sym -> value
          }

        date -> values
      }

    res.toMap
  }

   

}
