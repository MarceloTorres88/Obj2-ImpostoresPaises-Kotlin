package ar.edu.unahur.obj2.impostoresPaises

class ObservatorioApi(var api: RestCountriesAPI = RestCountriesAPI()) {

    // ver de SI utilizar el modo con lista de paises tratando de utilizar el " lazy val " para no iniciar algo hasta no ser llamado. EJ:
//    val test: String by lazy {
//        "some value" // esto no se ejecuta hasta que alguien use el valor test
//    }

//    var listaPaises = mutableSetOf<Pais>()

//    fun agregarPaises(){
//        listaPaises.addAll(api.todosLosPaises().map { AdapterCountry(it) })
//    }

//    init { this.agregarPaises() }

    fun encontrarPais(pais: String) : Pais {
        return  AdapterCountry (api.buscarPaisesPorNombre(pais).first())
    }

    fun cincoPaisesConMayorPoblacion(): List<String> {
        val variable = api.todosLosPaises().sortedByDescending { it.population }.map{it.name}
        return variable.subList(0,5)
    }

    fun continenteMasPoblado() : String =
        api.todosLosPaises().groupBy { it.region }
            .mapValues { entry -> entry.value.sumOf { it.population }
                .toFloat()  }.map { it.key }.first()

    fun sonLimitrofes(pais1: String, pais2: String): Boolean {
        val aux1 = this.encontrarPais(pais1)
        val aux2 = this.encontrarPais(pais2)

        return aux1.esLimitrofeCon(aux2)
    }

    fun necesitanTraduccion(pais1: String, pais2: String): Boolean {
        val aux1 = this.encontrarPais(pais1)
        val aux2 = this.encontrarPais(pais2)

        return aux1.necesitaTraductorCon(aux2)
    }

    fun sonPotencialesAliados(pais1: String, pais2: String): Boolean {
        val aux1 = this.encontrarPais(pais1)
        val aux2 = this.encontrarPais(pais2)

        return aux1.sonPotencialesAliados(aux2)
    }

}