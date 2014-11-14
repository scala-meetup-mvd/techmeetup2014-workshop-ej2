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
    parseFile(aaplSmall) should have size 8
  }

  
  // Debe poder construir un row a partir de una linea
  it should "be able to build a row from a line" in {
    parseLine(line).map(_.columns.size) should be (Some(6)) // 7 columns less date column, 6.
    parseLine(line).flatMap(_.columns.get("Open")) should be (Some(666.85))
    parseLine(line).flatMap(_.columns.get("High")) should be (Some(669.90))
    parseLine(line).flatMap(_.columns.get("Low")) should be (Some(656.00))
    parseLine(line).flatMap(_.columns.get("Close")) should be (Some(669.79))
    parseLine(line).flatMap(_.columns.get("Volume")) should be (Some(25410600))
    parseLine(line).flatMap(_.columns.get("AdjClose")) should be (Some(669.79))
  }

  // Debe poder construir un row a partir de una linea rota
  it should "be able to build a row from a malformed line" in {
    parseLine(linePartial).map(_.columns.size) should be (Some(4)) // 5 columns less date column, 4.
    parseLine(linePartial).flatMap(_.columns.get("Open")) should be (Some(666.85))
    parseLine(linePartial).flatMap(_.columns.get("High")) should be (Some(669.90))
    parseLine(linePartial).flatMap(_.columns.get("Low")) should be (None)
    parseLine(linePartial).flatMap(_.columns.get("Close")) should be (Some(669.79))
    parseLine(linePartial).flatMap(_.columns.get("Volume")) should be (None)
    parseLine(linePartial).flatMap(_.columns.get("AdjClose")) should be (Some(669.79))
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

  it should "return accurate data if missing column" in {

    val dates = Seq("2012-09-12")
    val syms  = Set("BRKN")

    val data = query(smallDataSetDir, syms, dates, "close")

    data should have size 1

    for {
      (date, values)  <- data
      (sym, value)    <- values
    } {
      println( s"Date $date values $values")
      assert( sym === syms.head  )
      assert( value === 669.79 )
    }

  }

}