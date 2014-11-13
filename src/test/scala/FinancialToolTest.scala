import util.{Solutions, ScalaMeetupSpec}

/** Tests to validate your implementation
  *
  */
class FinancialToolTest extends ScalaMeetupSpec with Solutions {

  import FinancialTool._

  "The Tool" should "for get correct files for a list symbols " in {
    findFiles(smallDataSetDir, symbols ) should have size 1
    findFiles(dataSetDir, symbols ) should have size 2
  }

  // Debe soportar simbolos no existentes, mezclados con existentes.
  it should "accept symbols that exist and symbols that not" in {
    findFiles(dataSetDir, badSymbols) should have size 2
  }

  // Debe soportar que no exista ningun symbol
  it should "return an empty file list if no symbol exists" in {
    findFiles(dataSetDir, noSymbols) should have size 0
  }

  // Debe leer solo las lineas relevantes
  it should "be able to get correct number of lines for a file" in {
    val files = findFiles(smallDataSetDir, symbols )
    val lines = readLines(files.head)
    lines should have size 8
  }

  // Debe poder construir un row a partir de una linea
  it should "be able to build a row from a line" in {
    val l = parseLine(line)
    l.columns should have size 6 // 7 columns less date column, 6.
  }

  // Debe poder construir un row a partir de una linea rota
  it should "be able to build a row from a malformed line" in {
    val l = parseLine(linePartial)
    l.columns should have size 6 // 7 columns less date column, 6.
  }

  // Para un sample X debe retornar data correcta.



}
