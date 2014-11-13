import java.text.SimpleDateFormat
import java.util.Date
import java.io.File

import scala.io.Source

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
      line <- allLines
    } yield {

    }
  }


  def parseLine(line:String): Option[Row]  = {
    val cell = line.split(",").lift
    val 
    for {
      date <- cell(0)
      open <- cell(1)
      high <- cell()
    }
  }

  def query(symbols: Seq[Sym], dates: Seq[Date], col: String): Map [Date, Seq[SymValue]] = ???


  def dateFromString(date: String): Date = {
    val simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD")
    simpleDateFormat.parse(date)
  }


}
