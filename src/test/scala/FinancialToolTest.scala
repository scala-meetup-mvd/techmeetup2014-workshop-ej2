import util.{Solutions, BaseSpec}

/** Tests to validate your implementation
  *
  */
class FinancialToolTest extends BaseSpec with Solutions {

  import FinancialTool._

  "The Tool" should "get correct files for a list symbols " in {
        symbols.map( findFile(dataSetDir, _) )
          .flatten should have size 2
  }

  // Debe soportar simbolos no existentes, mezclados con existentes.
  it should "accept symbols that exist and symbols that not" in {
    badSymbols.map( findFile(dataSetDir, _) )
      .flatten should have size 2
  }

  // Debe soportar que no exista ningun symbol
  it should "return an empty file list if no symbol exists" in {
    noSymbols.map( findFile(dataSetDir, _) )
      .flatten should have size 0
  }

  // Debe leer solo las lineas relevantes
  it should "be able to get correct number of lines for a file" in {
    readLines(aaplSmall) should have size 8
  }

  // Debe poder construir un row a partir de una linea
  it should "be able to build a row from a line" in {
    val l = parseLine(line)
    parseLine(line).map(_.columns.size) should be (Some(6)) // 7 columns less date column, 6.
  }

  // Debe poder construir un row a partir de una linea rota
  it should "be able to build a row from a malformed line" in {
    val l = parseLine(linePartial)
    parseLine(linePartial).map(_.columns.size) should be (Some(4)) // 5 columns less date column, 4.
  }

  it should "return accurate data for one symbol and one date" in {

    val dates = Seq("2012-09-12")
    val syms  = Set("AAPL")

    val data = query(dataSetDir, syms, dates, "open")

    data should have size 1

    for {
      (date, values)  <- data
      (sym, value)    <- values
    } {
      println( s"Date $date values $values")
      assert( sym === syms.head  )
      assert( value === 666.85 )
    }

  }

}