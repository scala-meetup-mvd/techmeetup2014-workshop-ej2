package util

trait Solutions {

  val aaplSmall     = "src/test/resources/small/AAPL.csv"

  val dataSetDir        = "src/test/resources/yahoo/"
  val smallDataSetDir   = "src/test/resources/small/"
  val badDataSetDir     = "src/test/resources/noexiste"

  val line          = "2012-09-12,666.85,669.90,656.00,669.79,25410600,669.79"
  val linePartial   = "2012-09-12,666.85,669.90,,669.79,,669.79"

  val symbols     = Set( "AAPL", "AMZN")
  val badSymbols  = Set( "AAPL", "AMZN", "BAD")
  val noSymbols  = Set( "NOEXISTENT", "BAD")



}