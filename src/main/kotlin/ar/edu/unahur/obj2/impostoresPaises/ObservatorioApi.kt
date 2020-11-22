package ar.edu.unahur.obj2.impostoresPaises

class ObservatorioApi(var api: RestCountriesAPI = RestCountriesAPI()) {

//    val listaPaises = mutableSetOf<Pais>()
//
//    fun agregarPaises(){
//        listaPaises.addAll(api.todosLosPaises().map { AdapterCountry(it) })
//    }
//
//    init { this.agregarPaises() }

    fun encontrarPais(pais: String) : Pais = api.buscarPaisesPorNombre(pais).map { AdapterCountry(it) }.first()
//    {
//        if(!this.listaPaises.any{it.name.contains(pais)}){
//            throw error("el pais $pais no esta en la lista")
//        }else{
//            return this.listaPaises.find{it.name.contains(pais)}!!
//        }
//    }

    fun cincoPaisesConMayorPoblacion()= api.todosLosPaises().sortedByDescending { it.population }.map{it.name}.take(5)


    fun continenteMasPoblado() : String =
        api.todosLosPaises().groupBy { it.region }. // agrupo paises por contienete
        mapValues{ pais -> pais.value.sumOf { it.population }}. // sumo las poblaciones de los paises en cada contiente
        maxByOrNull { it.value }!!.key // devuelvo la key del continente mas poblado


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