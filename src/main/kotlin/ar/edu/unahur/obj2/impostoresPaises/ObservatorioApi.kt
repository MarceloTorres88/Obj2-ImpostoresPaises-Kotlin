package ar.edu.unahur.obj2.impostoresPaises

object ObservatorioApi {

    var api = RestCountriesAPI()

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
//        if(!this.listaPaises.any{it.name == pais}){
//            throw error("el pais $pais no esta en la lista")
//        }else{
//            return this.listaPaises.find{it.name == pais}!!
//        }
    }

    fun cincoPaisesConMayorPoblacion(): List<String> {
        val variable = api.todosLosPaises().sortedByDescending { it.population }.map{it.name}
        return variable.subList(0,5)
    }

    private fun filtrarPorContinente(continente : String) =  api.todosLosPaises().filter { it.region == continente }

    private fun sumaPoblacionPorContinente(continente: String) = this.filtrarPorContinente(continente).sumOf{ it.population.toLong() }


    fun continenteMasPoblado() : String{
        val africa = this.sumaPoblacionPorContinente("Africa")
        val americas = this.sumaPoblacionPorContinente("Americas")
        val asia = this.sumaPoblacionPorContinente("Asia")
        val europe = this.sumaPoblacionPorContinente("Europe")
        val oceania = this.sumaPoblacionPorContinente("Oceania")

        val mayorpoblacionPorContiennte = listOf(africa,americas,asia,europe,oceania).maxOf { it }

        return when {
            mayorpoblacionPorContiennte == africa -> "Africa"
            mayorpoblacionPorContiennte == americas -> "Americas"
            mayorpoblacionPorContiennte == asia -> "Asia"
            mayorpoblacionPorContiennte == europe -> "Europe"
            else -> "Oceania"
        }
    }

    // No se si popdriamos dejar pasar de la primera parte de buscar y entregar el objeto pais para verificar que si son limitrofes.
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