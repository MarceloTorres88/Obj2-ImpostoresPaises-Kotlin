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

    fun cincoPaisesConMayorPoblacion()= this.listaPaises.sortedByDescending { it.population }.map{it.name}.take(5)

    fun continenteMasPoblado():String=
        this.listaPaises.groupBy { it.region }. // agrupo paises por contienete
        mapValues{ pais -> pais.value.sumOf { it.population }}. // sumo las poblaciones de los paises en cada contiente
        maxByOrNull { it.value }!!.key // devuelvo la key del continente mas poblado

}