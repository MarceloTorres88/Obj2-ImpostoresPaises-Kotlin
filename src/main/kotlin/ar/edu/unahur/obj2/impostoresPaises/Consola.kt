package ar.edu.unahur.obj2.impostoresPaises

import kotlin.math.ln1p

object Opcion{
    var opcion = 0
}


fun main () {
    menu()
    Opcion.opcion = readLine()!!.toInt()
    while (Opcion.opcion != 0){
        when {
            Opcion.opcion == 1 -> primeraOpcion()
            Opcion.opcion == 2 -> segundaOpcion()
            Opcion.opcion == 3 -> terceraOpcion()
            Opcion.opcion == 4 -> cuartaOpcion()
            Opcion.opcion == 5 -> quintaOpcion()
            else -> errorReingrese()
        }
    }
    println("¡Gracias por usar nuestro programa!")
}

fun menu(){
    println("Menu principal"+"\n"+"Ingrese numero de operacion:"+"\n"+"\n"+"1 - Info de un pais."+"\n"+ "2 - Paises limitrofes."
            +"\n"+ "3 - Paises que pueden dialogar sin interprete."+"\n"+ "4 - Los 5 paises con mayor poblacion."+"\n"+
            "5 - Contiente mas poblado."+"\n"+"\n"+"0 - Salir")
}

fun primeraOpcion() {
    println("Ingrese el nombre de un pais")
    val pais1 = ObservatorioApi.encontrarPais(readLine()!!)
    println("el pais ${pais1.name} tiene ${pais1.population} habitantes y habla ${pais1.languages.toString()} idiomas")
    menu()
    Opcion.opcion = readLine()!!.toInt()
}

fun segundaOpcion() {
    TODO("Not yet implemented")
}

fun terceraOpcion() {
    TODO("Not yet implemented")
}

fun cuartaOpcion() {
    TODO("Not yet implemented")
}

fun quintaOpcion() {
    TODO("Not yet implemented")
}

fun errorReingrese() {
    println("Error al ingreso de datos."+"\n"+"Ingrese nuevamente")
    Opcion.opcion = readLine()!!.toInt()
}


//menu principal
//  para ingresar el numero hacerlo en un when , que si no es uno de los numeros deseados, que reingrese
// 1 info del pais
    // ingrese el pais
    // fun busquedaPais
        // rtn "el pais $ pais tiene $pais.habitantes habitantes y habla $pais.idiomas idiomas"
// 2 son limitrofes con
    // "ingrese el primer pais"
    // fun busquedaPais
    // "ingrese segundo pais"
    // fun busquedaPais
        // fun son limitrofes -> "si , son limitrofes $pais1 y $pais2"/"no , no son limitrofes $pais1 y $pais2"
// 3 pueden dialogar
    // "ingrese el primer pais"
    // fun busquedaPais
    // "ingrese segundo pais"
    // fun busquedaPais
        // fun son limitrofes -> "si , pueden dialogar ciudadanos de $pais1 y $pais2"/"no , no pueden dialogar ciudadanos de $pais1 y $pais2"
// 4 son potenciales aliados
    // "ingrese el   primer pais"
    // fun busquedaPais
    // "ingrese segundo pais"
    // fun busquedaPais
        // fun son limitrofes -> "si , son potenciales aliados $pais1 y $pais2"/"no , no son potenciales aliados $pais1 y $pais2"
// 5 5 paises con mayor poblacion
    // fun paisesConMayorPobalcion -> "los 5 paises mas pobladon son: " + ln(este comando hace linea a parte) +
        // $list.get(1) + ln +
        // $list.get(2) + ln +
        // $list.get(3) + ln +
        // $list.get(4) + ln +
        // $list.get(5)
// 6 continente mas poblado
    // fun contiente mas poblado -> "el continente mas poblado es $continente y tiene $ habitantes (hacerlo long) habitantes
// 0 cerrar -> while readline!= 0 (todo el programa dentro de este while)