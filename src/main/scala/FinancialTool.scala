import java.text.SimpleDateFormat
import java.util.Date

import java.nio.file.{ Files, Paths }

import scala.collection.JavaConversions._
import scala.io.Source

/** XXX
 * Created by f on 11/12/14.
 */
object FinancialTool {

  type Sym      = String
  type SymValue = (Sym, Double)

  type Column = (String, Double)
  case class Row(date: Date, columns: Map[String, Double])

  val columnNames = Seq("Open","High","Low","Close","Volume", "AdjClose")

  val NameExtR = """(.*)\.([^.]*)""".r

  def findFiles(root: String, symbols: Set[Sym]): Seq[String] = {
    val javaStream = Files.list(Paths.get(root))

    val scalaStream = javaStream.iterator.toStream // usa JavaConversions._

    for {
      path <- scalaStream
      fileName = path.getFileName.toString
      Seq(name, ext) <- NameExtR.unapplySeq(fileName)
      if ext.toLowerCase == "csv" && symbols.contains(name)
    } yield {
      path.toString
    }
  }

  def allLines(file: String): Seq[String] = Source.fromFile(file).getLines

  private def parseColumnNames: Seq[String] = ???

  def readLines(file: String):  Seq[String] = Source.fromFile(file).getLines.toSeq



  def parseLine(line:String): Row  = ???

  def query(symbols: Seq[Sym], dates: Seq[Date], col: String): Map [Date, Seq[SymValue]] = ???


  private def dateFromString(date: String): Date = {
    val simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD")
    simpleDateFormat.parse(date)
  }


}
