import java.text.SimpleDateFormat
import java.util.Date
import java.io.File

import scala.io.Source
import scala.util.Try

/** XXX
 * Created by f on 11/12/14.
 */
object FinancialTool {

  type Sym      = String
  type SymValue = (Sym, Double)
  type Column   = (String, Double)

  case class Row(date: Date, columns: Map[String, Double])

  val columnNames = Seq("Open","High","Low","Close","Volume", "AdjClose")


  def findFile(root: String, symbol: Sym): Option[String] = {
    val file = new File(root, s"$symbol.csv")
    
    if (file.exists) Some(file.getCanonicalPath) else None
  }

  def allLines(file: String):  Seq[String] = Source.fromFile(file).getLines.toStream 

  def readLines(file: String):  Seq[String] = {
    for {
      line <- allLines(file)
      if parseLine(line).isDefined
    } yield {
      line
    }
  }

  def parseLine(line:String): Option[Row]  = {
    val parts = line.split(",")
    val optDateStr = parts.lift(0)
    val optDate = 
      for {
        dateStr <- optDateStr
        tryDate = Try(dateFromString(dateStr))
        date <- tryDate.toOption
      } yield {
        date
      }

    for {
      date <- optDate
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

  def query(root: String, symbols: Seq[Sym], dates: Seq[Date], col: String): Map [Date, Seq[SymValue]] = {
    val symRows =  
      symbols
        .flatMap(sym => findFile(root, sym).map(sym -> _))
        .toMap
        .mapValues(file => allLines(file).flatMap(parseLine))

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


  def dateFromString(date: String): Date = {
    val simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD")
    simpleDateFormat.parse(date)
  }


}
