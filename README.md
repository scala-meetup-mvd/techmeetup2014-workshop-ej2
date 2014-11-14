
# Scala Workshop - Tech Meetup 2014

## Ejercicio 2

#### Objetivo

El objetivo es interpretar archivos CSV con información financiera.

Estos archivos contienen cada uno información de movimientos de bolsa, uno registro por fecha, una empresa por archivo y
tienen por nombre el símbolo con el cual la empresa cotiza en bolsa (US), por ejemplo: AAPL.csv, GOOG.csv, AMZN.csv.

Los datos en el CSV deben considerarse opcionales, en el sentido de que algunos pueden no estar presentes.

Dados un directorio con archivos CSV, una lista de símbolos, una lista de fechas y el nombre de una columna construir
una estructura que contenga para cada fecha, el valor de la columna para cada símbolo.



    [GOOG, AAPL] [2012-09-12, 2012-09-02] "LOW" =>
        2012-09-02, (AAPL, 669.90) (GOOG, ...)
        2012-09-12,  ...

#### Implementación y Tests

En `src/test/scala/` encontrarás un archivo `XXXTest` con los tests vacíos para validar la implementación.
En `src/main/scala` el archivo `XXX` con funciones sin implementación para completar.

#### Técnicas que puedes aplicar

* Mirar la documentación de collections es importante.


