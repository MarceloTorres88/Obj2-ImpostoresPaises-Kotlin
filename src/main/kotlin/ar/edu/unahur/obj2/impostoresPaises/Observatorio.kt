package ar.edu.unahur.obj2.impostoresPaises

object Observatorio {
    val listaPaises = mutableSetOf<Pais>()

    private fun encontrarPais(pais: String) : Pais {
        if(!this.listaPaises.contains(pais)){
            throw error("el pais $pais no esta en la lista") // o mejorar el texto del error como convenga
        }else{
            return this.listaPaises.find{it.name == pais}!!
        }
    }

    fun agregarPais(pais: Pais) = this.listaPaises.add(pais)

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

    fun cincoPaisesConMayorPoblacion() = this.listaPaises.sortedBy { it.population }.subList(0,4)

    fun continenteMasPoblado() = this.listaPaises
}