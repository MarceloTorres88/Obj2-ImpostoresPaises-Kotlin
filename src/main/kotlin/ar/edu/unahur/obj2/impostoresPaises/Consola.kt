package ar.edu.unahur.obj2.impostoresPaises

import okhttp3.internal.wait

object Opcion{ // solo para guardar un numero y que sea alcanzable por todas las opciones
    var opcion = 0
}


fun main () {
    menu()
    try {
        Opcion.opcion = readLine()!!.toInt() // ingresa el valor
        while (Opcion.opcion != 0){ // con esto se queda en el menu resolviendo todas las consultas que quiera hasta que tiren el 0
            when {
                Opcion.opcion == 1 -> primeraOpcion()
                Opcion.opcion == 2 -> opcionDosTresCuatro()
                Opcion.opcion == 3 -> opcionDosTresCuatro()
                Opcion.opcion == 4 -> opcionDosTresCuatro()
                Opcion.opcion == 5 -> quintaOpcion()
                Opcion.opcion == 6 -> sextaOpcion()
                else -> errorReingresoMenu()
            }
        }
    }
    catch (e: Exception){
        println("El menu se maneja solo con numeros del 0 al 6")
    }
    finally {
        println("¡Gracias por usar nuestro programa!")
    }
} // aca termina el programa

fun menu(){ // puse esto para que no se repita 700 veces estas lineas
    println("Menu principal"+"\n"+"Ingrese numero de operacion:"+"\n"+"\n"+"1 - Info de un pais."+"\n"+ "2 - Paises limitrofes."
            +"\n"+ "3 - Paises que pueden dialogar sin interprete."+"\n"+ "4 - Paises potenciales aliados."
            +"\n"+ "5 - Los 5 paises con mayor poblacion."+"\n"+ "6 - Contiente mas poblado."+"\n"+"\n"+"0 - Salir")
}

fun primeraOpcion() {
    println("Ingrese el nombre de un pais")
    try { // intenta buscar el pais e imprimir la informacion
        val api = RestCountriesAPI()
        val pais1 = ObservatorioApi(api).encontrarPais(readLine()!!)
        println("El pais ${pais1.name} tiene ${pais1.population} habitantes y habla ${pais1.languages}"+"\n")
    }
    catch (e: Exception){ // si hay error lo dice
        println("No existe tal pais. Volvemos al menu.")
    }
    finally {// sale al menu.
        volverAlMenu()
    }
}

//fun segundaOpcion() {
//    println("Ingrese el nombre de un pais")
//    val pais1  = readLine()!!
//    println("Ingrese el nombre de otro un pais")
//    val pais2 = readLine()!!
//    // comprobacion de que ambos existen y que no son el mismo. reingresar
//    println("los paises $pais1 y $pais2 "+if(ObservatorioApi.sonLimitrofes(pais1,pais2)){"si"}else{"no"}+ " son limitrofes."+"\n")
//    volverAlMenu()
//}
//
//fun terceraOpcion() {
//    println("Ingrese el nombre de un pais")
//    val pais1  = readLine()!!
//    println("Ingrese el nombre de otro un pais")
//    val pais2 = readLine()!!
//    // comprobacion de que ambos existen y que no son el mismo. reingresar
//    println("los paises $pais1 y $pais2 "+if(ObservatorioApi.necesitanTraduccion(pais1,pais2)){"no"}else{""}+ " pueden dialogar sin interprete."+"\n")
//    volverAlMenu()
//}
//
//fun cuartaOpcion() {
//    println("Ingrese el nombre de un pais")
//    val pais1  = readLine()!!
//    println("Ingrese el nombre de otro un pais")
//    val pais2 = readLine()!!
//    // comprobacion de que ambos existen y que no son el mismo. reingresar
//    println("los paises $pais1 y $pais2 "+if(ObservatorioApi.sonPotencialesAliados(pais1,pais2)){""}else{"no"}+ " son potenciales aliados."+"\n")
//    volverAlMenu()
//}

fun opcionDosTresCuatro(){
    val api = RestCountriesAPI()
    println("Ingrese el nombre de un pais")
    val pais1  = readLine()!!
    println("Ingrese el nombre de otro un pais")
    var pais2 = readLine()!!
    while (pais2 == pais1){
        println("No se puede comparar un pais con sigo mismo."+"\n"+"Ingrese el nombre de otro pais.")
        pais2 = readLine()!!
    }
    // comprobacion de que ambos existen. reingresar
    try {
        println("los paises $pais1 y $pais2 "+ // aca hice magia , y con el mismo metodo , meti las 3 opciones.
                when{
                    Opcion.opcion == 2 -> if(ObservatorioApi(api).sonLimitrofes(pais1,pais2)){"si"}else{"no"}+ " son limitrofes."+"\n"
                    Opcion.opcion == 3 -> if(ObservatorioApi(api).necesitanTraduccion(pais1,pais2)){"no"}else{""}+ " pueden dialogar sin interprete."+"\n"
                    else -> if(ObservatorioApi(api).sonPotencialesAliados(pais1,pais2)){""}else{"no"}+ " son potenciales aliados."+"\n"
                })
    }
    catch (e: Exception){
        println("Algo salio mal.")
    }
    finally {
        volverAlMenu()
    }
}

fun quintaOpcion() {
    val api = RestCountriesAPI()
    val listaPaises = ObservatorioApi(api).cincoPaisesConMayorPoblacion()
    // lista los paises con salto de linea.
    println("Los 5 paises con mayor poblacion son:"+"\n"+ listaPaises.get(0) +"\n"+ listaPaises.get(1) +
            "\n"+ listaPaises.get(2) +"\n"+ listaPaises.get(3) +"\n"+ listaPaises.get(4) +"\n")
    volverAlMenu()
}

fun sextaOpcion() {
    val api = RestCountriesAPI()
    println("El continenete mas poblado es:"+"\n"+ObservatorioApi(api).continenteMasPoblado()+"\n")
    volverAlMenu()
}

fun errorReingresoMenu() {
    println("Error al ingreso de datos."+"\n"+"Ingrese nuevamente"+"\n")
    Opcion.opcion = readLine()!!.toInt()
}

fun volverAlMenu(){
    menu()
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