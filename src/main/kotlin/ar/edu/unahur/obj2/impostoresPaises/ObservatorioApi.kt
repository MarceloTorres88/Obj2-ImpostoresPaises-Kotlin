package ar.edu.unahur.obj2.impostoresPaises

object ObservatorioApi {

    private val api = RestCountriesAPI()

    val listaPaises = mutableSetOf<Pais>()

    fun agregarPaises(){
        listaPaises.addAll(api.todosLosPaises().map { AdapterCountry(it) })
    }

    init { this.agregarPaises() } // no se por que , pero arriba  de todo no me lo levanta . quizas la funcion no estaba creada.

    // lo que decia el profe , no es asi. en el metodo anterior se cargan todos los paises , y en este se buscan solo los 2 paises
    //necesarios. no se cargan todos los paises cada vez que se ejecuta un metodo
    fun encontrarPais(pais: String) : Pais {
        if(!this.listaPaises.any{it.name == pais}){
            throw error("el pais $pais no esta en la lista")
        }else{
            return this.listaPaises.find{it.name == pais}!!
        }
    }

    fun cincoPaisesConMayorPoblacion(): List<String> {
        val variable = this.listaPaises.sortedByDescending { it.population }.map{it.name}
        return variable.subList(0,5)
    }

    private fun filtrarPorContinente(continente : String) =  this.listaPaises.filter { it.region == continente }

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

        return !this.necesitanTraduccion(pais1,pais2) and aux1.comparteBloqueRegionalCon(aux2)
    }

}