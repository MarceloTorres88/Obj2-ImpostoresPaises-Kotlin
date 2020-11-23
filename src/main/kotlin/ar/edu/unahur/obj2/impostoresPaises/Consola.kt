package ar.edu.unahur.obj2.impostoresPaises

fun main () {
    Programa.iniciar()
}

object EntradaSalida{
    fun leerLinea() = readLine()!!
    fun escribirLinea(contenido: String) { println(contenido) }
}

object Programa{
    private var opcionMenu = 0
    private val api = RestCountriesAPI()
    private val ObsvApi = ObservatorioApi(api)
    var entradaSalida :EntradaSalida = EntradaSalida


    private fun lineaEnBlanco() = entradaSalida.escribirLinea("")
    private fun leerNumero() = entradaSalida.leerLinea().toInt()
    private fun ingresoOpcionMenu(){ opcionMenu = leerNumero() }

    fun iniciar(){ menu() }

    private fun menuEscrito(){
        lineaEnBlanco()
        entradaSalida.escribirLinea("Menu principal")
        entradaSalida.escribirLinea("Ingrese numero de operacion:")
        entradaSalida.escribirLinea("1 - Info de un pais.")
        entradaSalida.escribirLinea("2 - Paises limitrofes.")
        entradaSalida.escribirLinea("3 - Paises que pueden dialogar sin interprete.")
        entradaSalida.escribirLinea("4 - Paises potenciales aliados.")
        entradaSalida.escribirLinea("5 - Los 5 paises con mayor poblacion.")
        entradaSalida.escribirLinea("6 - Contiente mas poblado.")
        entradaSalida.escribirLinea("0 - Salir")
        lineaEnBlanco()
    }

    private fun menu(){
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
            entradaSalida.escribirLinea("El menu se maneja solo con numeros del 0 al 6")
        }
        finally {
            entradaSalida.escribirLinea("¡Gracias por usar nuestro programa!")
        }
    }

    private fun primeraOpcion() {
        entradaSalida.escribirLinea("Ingrese el nombre de un pais")
        try { // intenta buscar el pais e imprimir la informacion
            val pais = ObsvApi.encontrarPais(entradaSalida.leerLinea())
            entradaSalida.escribirLinea("El pais ${pais.name} tiene ${pais.population} habitantes y habla ${pais.languages}")
        }
        catch (e: Exception){ // si hay error lo dice
            entradaSalida.escribirLinea("No existe tal pais, volvemos al menu.")
        }
        finally { // muestra de nuevo el menu y espera un numero en opcionMenu
            volverAlMenu()
        }
    }

    private fun opcionDosTresCuatro(){
        entradaSalida.escribirLinea("Ingrese el nombre de un pais")
        val pais1  = ObsvApi.encontrarPais(entradaSalida.leerLinea())
        entradaSalida.escribirLinea("Ingrese el nombre de otro pais")
        var pais2 = ObsvApi.encontrarPais(entradaSalida. leerLinea())
        while (pais2 == pais1){ // comprobacion de que no son iguales
            entradaSalida.escribirLinea("No se puede comparar un pais con sigo mismo.")
            entradaSalida.escribirLinea("Ingrese el nombre de otro pais.")
            pais2 = ObsvApi.encontrarPais(entradaSalida. leerLinea())
        }
        try {// intenta hacer la operacion
            entradaSalida.escribirLinea(  // aca hice magia , y con el mismo metodo , meti las 3 opciones.
                "los paises ${pais1.name} y ${pais2.name} "+
                        when (opcionMenu) { // segun opcion del menu es la respuesta
                            2 -> if(ObsvApi.sonLimitrofes(pais1.name,pais2.name)){""}else{"no"}+ " son limitrofes."+"\n"
                            3 -> if(ObsvApi.necesitanTraduccion(pais1.name,pais2.name)){"no"}else{""}+ " pueden dialogar sin interprete."+"\n"
                            else -> if(ObsvApi.sonPotencialesAliados(pais1.name,pais2.name)){""}else{"no"}+ " son potenciales aliados."+"\n"
                        }
            )
        }
        catch (e: Exception){ // si hay error lo dice
            entradaSalida.escribirLinea("Se ingreso mal un pais.")
        }
        finally { // muestra de nuevo el menu y espera un numero en opcionMenu
            volverAlMenu()
        }
    }

    private fun quintaOpcion() {
        val listaPaises = ObsvApi.cincoPaisesConMayorPoblacion()
        entradaSalida.escribirLinea("Los 5 paises con mayor poblacion son:")
        entradaSalida.escribirLinea(listaPaises[0])
        entradaSalida.escribirLinea(listaPaises[1])
        entradaSalida.escribirLinea(listaPaises[2])
        entradaSalida.escribirLinea(listaPaises[3])
        entradaSalida.escribirLinea(listaPaises[4])
        volverAlMenu()
    }

    private fun sextaOpcion() {
        entradaSalida.escribirLinea("El continenete mas poblado es:")
        entradaSalida.escribirLinea(ObsvApi.continenteMasPoblado())
        volverAlMenu()
    }

    private fun errorReingresoMenu() {
        entradaSalida.escribirLinea("Error al seleccionar opcion.")
        entradaSalida.escribirLinea("Ingrese nuevamente.")
        ingresoOpcionMenu()
    }

    private fun volverAlMenu(){
        menuEscrito()
        ingresoOpcionMenu()
    }
}




