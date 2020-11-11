package ar.edu.unahur.obj2.impostoresPaises

object ObservatorioApi {
    private val api = RestCountriesAPI()

    val listaPaises = mutableSetOf<Pais>()

    fun agregarPaises(){
        listaPaises.addAll(api.todosLosPaises().map { AdapterCountry(it) })
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

}