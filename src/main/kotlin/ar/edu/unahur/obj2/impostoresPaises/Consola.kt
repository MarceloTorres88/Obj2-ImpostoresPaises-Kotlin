package ar.edu.unahur.obj2.impostoresPaises

fun main () {
    Programa.iniciar()
}

object Programa{
    var opcionMenu = 0
    val api = RestCountriesAPI()
    val ObsvApi = ObservatorioApi(api)

    fun leerLinea() = readLine()!!
    fun escribirLinea(contenido: String) { println(contenido) }
    fun lineaEnBlanco() = escribirLinea("")
    fun leerNumero() = leerLinea().toInt()
    fun ingresoOpcionMenu(){ opcionMenu = leerNumero() }

    fun iniciar(){ menu() }

    fun menuEscrito(){
        lineaEnBlanco()
        escribirLinea("Menu principal")
        escribirLinea("Ingrese numero de operacion:")
        escribirLinea("1 - Info de un pais.")
        escribirLinea("2 - Paises limitrofes.")
        escribirLinea("3 - Paises que pueden dialogar sin interprete.")
        escribirLinea("4 - Paises potenciales aliados.")
        escribirLinea("5 - Los 5 paises con mayor poblacion.")
        escribirLinea("6 - Contiente mas poblado.")
        escribirLinea("0 - Salir")
        lineaEnBlanco()
    }

    fun menu(){
        menuEscrito()
        try {
            ingresoOpcionMenu()
            while (opcionMenu != 0){ // con esto se queda en el menu resolviendo todas las consultas que quiera hasta que tiren el 0
                when (opcionMenu) {
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
            escribirLinea("El menu se maneja solo con numeros del 0 al 6")
        }
        finally {
            escribirLinea("¡Gracias por usar nuestro programa!")
        }
    }

    fun primeraOpcion() {
        escribirLinea("Ingrese el nombre de un pais")
        try { // intenta buscar el pais e imprimir la informacion
            val pais = ObsvApi.encontrarPais(leerLinea())
            escribirLinea("El pais ${pais.name} tiene ${pais.population} habitantes y habla ${pais.languages}")
        }
        catch (e: Exception){ // si hay error lo dice
            escribirLinea("No existe tal pais, volvemos al menu.")
        }
        finally { // muestra de nuevo el menu y espera un numero en opcionMenu
            volverAlMenu()
        }
    }

    fun opcionDosTresCuatro(){
        escribirLinea("Ingrese el nombre de un pais")
        val pais1  = leerLinea()
        escribirLinea("Ingrese el nombre de otro pais")
        var pais2 = leerLinea()
        while (pais2 == pais1){ // comprobacion de que no son iguales
            escribirLinea("No se puede comparar un pais con sigo mismo.")
            escribirLinea("Ingrese el nombre de otro pais.")
            pais2 = leerLinea()
        }
        try {// intenta hacer la operacion
            escribirLinea(  // aca hice magia , y con el mismo metodo , meti las 3 opciones.
                "los paises $pais1 y $pais2 "+
                        when (opcionMenu) { // segun opcion del menu es la respuesta
                            2 -> if(ObsvApi.sonLimitrofes(pais1,pais2)){""}else{"no"}+ " son limitrofes."+"\n"
                            3 -> if(ObsvApi.necesitanTraduccion(pais1,pais2)){"no"}else{""}+ " pueden dialogar sin interprete."+"\n"
                            else -> if(ObsvApi.sonPotencialesAliados(pais1,pais2)){""}else{"no"}+ " son potenciales aliados."+"\n"
                        }
            )
        }
        catch (e: Exception){ // si hay error lo dice
            escribirLinea("Se ingreso algun pais mal.")
        }
        finally { // muestra de nuevo el menu y espera un numero en opcionMenu
            volverAlMenu()
        }
    }

    fun quintaOpcion() {
        val listaPaises = ObsvApi.cincoPaisesConMayorPoblacion()
        escribirLinea("Los 5 paises con mayor poblacion son:")
        escribirLinea(listaPaises[0])
        escribirLinea(listaPaises[1])
        escribirLinea(listaPaises[2])
        escribirLinea(listaPaises[3])
        escribirLinea(listaPaises[4])
        volverAlMenu()
    }

    fun sextaOpcion() {
        escribirLinea("El continenete mas poblado es:")
        escribirLinea(ObsvApi.continenteMasPoblado())
        volverAlMenu()
    }

    fun errorReingresoMenu() {
        escribirLinea("Error al seleccionar opcion.")
        escribirLinea("Ingrese nuevamente.")
        ingresoOpcionMenu()
    }

    fun volverAlMenu(){
        menuEscrito()
        ingresoOpcionMenu()
    }
}




