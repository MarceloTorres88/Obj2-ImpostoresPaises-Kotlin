package ar.edu.unahur.obj2.impostoresPaises

object Observatorio {
    val listaPaises = mutableSetOf<Pais>()

    init { listaPaises.addAll(setOf(Argentina,Brasil,Peru,Bolivia,Spain)) }

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
        val paisUno = this.encontrarPais(pais1)
        val paisDos = this.encontrarPais(pais2)

        return paisUno.esLimitrofeCon(paisDos)
    }

    fun necesitanTraduccion(pais1: String, pais2: String): Boolean {
        val paisUno = this.encontrarPais(pais1)
        val paisDos = this.encontrarPais(pais2)

        return paisUno.necesitaTraductorCon(paisDos)
    }

    fun sonPotencialesAliados(pais1: String, pais2: String): Boolean {
        val paisUno = this.encontrarPais(pais1)
        val paisDos = this.encontrarPais(pais2)

        return paisUno.sonPotencialesAliados(paisDos)
    }

    fun cincoPaisesConMayorPoblacion()= this.listaPaises
        .sortedByDescending { it.population }  //ordena los paises por poblacion de forma descenente
        .map{it.name} // solo deja los nombres ordenados
        .take(5) // toma los primeros 5

    fun continenteMasPoblado():String=
        this.listaPaises.groupBy { it.region }. // agrupo paises por contienete
        mapValues{ pais -> pais.value.sumOf { it.population }}. // sumo las poblaciones de los paises en cada contiente
        maxByOrNull { it.value }!!.key // devuelvo la key del continente mas poblado

}