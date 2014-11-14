import java.text.SimpleDateFormat

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

  def findFile(root: String, symbol: Sym): Option[String] = ???

  def parseFile(file: String):  Seq[Row] = ???

  def parseLine(line:String): Option[Row]  = ???

  def query(root: String, symbols: Set[Sym], dates: Seq[String], col: String): Map [String, Seq[SymValue]] = ???



}
