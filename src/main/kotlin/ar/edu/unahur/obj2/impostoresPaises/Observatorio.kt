package ar.edu.unahur.obj2.impostoresPaises

object Observatorio {
    val listaPaises = mutableSetOf<Pais>()

    private fun encontrarPais(pais: String) : Pais {
        if(!this.listaPaises.any{it.name == pais}){
            throw error("el pais $pais no esta en la lista")
        }else{
            return this.listaPaises.find{it.name == pais}!!
        }
    }

    fun agregarPais(pais: Pais) {
                this.listaPaises.add(pais)
    }

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

    fun cincoPaisesConMayorPoblacion(): List<String> {
        val variable = this.listaPaises.sortedByDescending { it.population }.map{it.name}
        return variable.subList(0,5)
    }

    private fun filtrarPorContinente(continente : String) =  this.listaPaises.filter { it.region == continente }

    private fun sumaPoblacionPorContinente(continente: String) = this.filtrarPorContinente(continente).sumBy { it.population }

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