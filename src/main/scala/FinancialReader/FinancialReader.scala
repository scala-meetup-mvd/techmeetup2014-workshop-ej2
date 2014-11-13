import java.util.Date

/** XXX
 * Created by f on 11/12/14.
 */
object FinancialReader {

  type Row      = Map[String, Double]
  type Sym      = String
  type SymValue = (Sym, Double)

  def findFiles(root: String, symbols: Set[Sym]): Seq[String] = ???

  def readLines(file: String):  Seq[String] = ???

  def parseLine(line:String): Row  = ???

  //def relevantDate(row: Row): Boolean = ???
  //def dateFromString

  def query(symbols: Seq[Sym], dates: Seq[Date], col: String): Map [Date, Seq[SymValue]] = ???

}
