package ar.edu.unahur.obj2.impostoresPaises


object Consola{ // solo para guardar un numero y que sea alcanzable por todas las opciones
    var opcionMenu = 0
    val api = RestCountriesAPI()
    val ObsvApi = ObservatorioApi(api)
    fun leerLinea() = readLine()!!
    fun escribirLinea(contenido: String) { println(contenido) }
    fun lineaEnBlanco() = escribirLinea("")
    fun leerNumero() = readLine()!!.toInt()
    fun ingresoOpcionMenu(){ opcionMenu = leerNumero() }
}


fun main () {
    correrMenu()
}

fun correrMenu(){
    menuEscrito()
    try {
        Consola.ingresoOpcionMenu() // ingresa el valor
        while (Consola.opcionMenu != 0){ // con esto se queda en el menu resolviendo todas las consultas que quiera hasta que tiren el 0
            when (Consola.opcionMenu) {
                1 -> primeraOpcion()
                2 -> opcionDosTresCuatro()
                3 -> opcionDosTresCuatro()
                4 -> opcionDosTresCuatro()
                5 -> quintaOpcion()
                6 -> sextaOpcion()
                else -> errorReingresoMenu()
            }
        }
    }
    catch (e: Exception){ // si no se ingresa un numero sale por aca
        Consola.escribirLinea("El menu se maneja solo con numeros del 0 al 6")
    }
    finally {
        Consola.escribirLinea("¡Gracias por usar nuestro programa!")
    }
}

fun menuEscrito(){ // puse esto para que no se repita 700 veces estas lineas
    Consola.lineaEnBlanco()
    Consola.escribirLinea("Menu principal")
    Consola.escribirLinea("Ingrese numero de operacion:")
    Consola.escribirLinea("1 - Info de un pais.")
    Consola.escribirLinea("2 - Paises limitrofes.")
    Consola.escribirLinea("3 - Paises que pueden dialogar sin interprete.")
    Consola.escribirLinea("4 - Paises potenciales aliados.")
    Consola.escribirLinea("5 - Los 5 paises con mayor poblacion.")
    Consola.escribirLinea("6 - Contiente mas poblado.")
    Consola.escribirLinea("0 - Salir")
    Consola.lineaEnBlanco()
}

fun primeraOpcion() {
    Consola.escribirLinea("Ingrese el nombre de un pais")
    try { // intenta buscar el pais e imprimir la informacion
        val pais1 = Consola.ObsvApi.encontrarPais(Consola.leerLinea())
        Consola.escribirLinea("El pais ${pais1.name} tiene ${pais1.population} habitantes y habla ${pais1.languages}"+"\n")
    }
    catch (e: Exception){ // si hay error lo dice
        Consola.escribirLinea("No existe tal pais, volvemos al menu.")
    }
    finally {// sale al menu.
        volverAlMenu()
    }
}

fun opcionDosTresCuatro(){
    Consola.escribirLinea("Ingrese el nombre de un pais")
    val pais1  = Consola.leerLinea()
    Consola.escribirLinea("Ingrese el nombre de otro un pais")
    var pais2 = Consola.leerLinea()
    while (pais2 == pais1){ // comprobacion de que no son iguales
        Consola.escribirLinea("No se puede comparar un pais con sigo mismo.")
        Consola.escribirLinea("Ingrese el nombre de otro pais.")
        pais2 = Consola.leerLinea()
    }
    try {// intenta hacer la operacion
        Consola.escribirLinea(  // aca hice magia , y con el mismo metodo , meti las 3 opciones.
            "los paises $pais1 y $pais2 "+
                when (Consola.opcionMenu) { // segun opcion del menu es la respuesta
                    2 -> if(Consola.ObsvApi.sonLimitrofes(pais1,pais2)){"si"}else{"no"}+ " son limitrofes."+"\n"
                    3 -> if(Consola.ObsvApi.necesitanTraduccion(pais1,pais2)){"no"}else{""}+ " pueden dialogar sin interprete."+"\n"
                    else -> if(Consola.ObsvApi.sonPotencialesAliados(pais1,pais2)){""}else{"no"}+ " son potenciales aliados."+"\n"
                }
        )
    }
    catch (e: Exception){ // si hay error lo dice
        Consola.escribirLinea("Se ingreso algun pais mal.")
    }
    finally {
        volverAlMenu()
    }
}

fun quintaOpcion() {
    val listaPaises = Consola.ObsvApi.cincoPaisesConMayorPoblacion()
    Consola.escribirLinea("Los 5 paises con mayor poblacion son:")
    Consola.escribirLinea(listaPaises[0])
    Consola.escribirLinea(listaPaises[1])
    Consola.escribirLinea(listaPaises[2])
    Consola.escribirLinea(listaPaises[3])
    Consola.escribirLinea(listaPaises[4])
    volverAlMenu()
}

fun sextaOpcion() {
    Consola.escribirLinea("El continenete mas poblado es:")
    Consola.escribirLinea(Consola.ObsvApi.continenteMasPoblado())
    volverAlMenu()
}

fun errorReingresoMenu() {
    Consola.escribirLinea("Error al seleccionar opcion.")
    Consola.escribirLinea("Ingrese nuevamente.")
    Consola.ingresoOpcionMenu()
}

fun volverAlMenu(){
    menuEscrito()
    Consola.ingresoOpcionMenu()
}