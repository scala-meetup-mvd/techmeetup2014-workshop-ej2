import java.text.SimpleDateFormat
import java.util.Date

/** XXX
 * Created by f on 11/12/14.
 */
object FinancialTool {

  type Sym      = String
  type SymValue = (Sym, Double)
  type Column   = (String, Double)

  case class Row(date: Date, columns: Map[String, Double])

  val columnNames = Seq("Open","High","Low","Close","Volume", "AdjClose")

  def findFile(root: String, symbol: Sym): Option[String] = ???

  def readLines(file: String):  Seq[String] = ???

  def parseLine(line:String): Option[Row]  = ???

  def query(root: String, symbols: Set[Sym], dates: Seq[String], col: String): Map [String, Seq[SymValue]] = ???



  def dateFromString(date: String): Date = {
    val simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD")
    simpleDateFormat.parse(date)
  }


}
