import util.{Solutions, ScalaMeetupSpec}

/** Tests to validate your implementation
  *
  */
class FinancialReaderTest extends ScalaMeetupSpec with Solutions {

  import FinancialReader._

  "Financial" should "for get correct files for a list symbols " in {
    findFiles(smallDataSetDir, symbols ) should have size 1
    findFiles(dataSetDir, symbols ) should have size 2
  }

  // Debe soportar simbolos no existentes, mezclados con existentes.
  it should "accept symbols that exist and symbols that not" in pendingU()

  // Debe soportar que no exista ningun symbol
  it should "return an empty dataset if no symbol exists" in pendingU()

  // Debe leer solo las lineas relevantes
  it should "be able to get correct number of lines for a file" in pendingU()

  // Debe poder construir un row a partir de una linea
  it should "be able to build a row from a line" in pendingU()

  // Debe poder construir un row a partir de una linea rota
  it should "be able to build a row from a malformed line" in pendingU()

  // Para un sample X debe retornar data correcta.



}
