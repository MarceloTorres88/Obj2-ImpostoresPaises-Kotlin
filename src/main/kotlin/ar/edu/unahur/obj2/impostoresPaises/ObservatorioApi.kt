package ar.edu.unahur.obj2.impostoresPaises

class ObservatorioApi(var api: RestCountriesAPI = RestCountriesAPI()) {
// Usamos la forma de hacer peticiones a la api por cada requerimiento por sobre
// la forma de cargar en por init() todos los paises y consultar la lista
// por que se dificultaba el uso del usuario en la consola, ya que esta requeria que los paises
// esten escritos exactamente como se llama el pais y no por parte del nombre.
// EJ: bolivia (caso peticiones api) <-> Bolivia (Plurinational State of) (caso peticiones lista)

    fun encontrarPais(pais: String) : Pais =
        api.buscarPaisesPorNombre(pais).map { AdapterCountry(it) }.first()

    fun cincoPaisesConMayorPoblacion()=
        api.todosLosPaises().sortedByDescending { it.population }.map{it.name}.take(5)


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