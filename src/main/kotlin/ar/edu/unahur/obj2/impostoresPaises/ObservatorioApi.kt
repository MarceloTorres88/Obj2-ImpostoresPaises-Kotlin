package ar.edu.unahur.obj2.impostoresPaises

object ObservatorioApi {
    private val api = RestCountriesAPI()

    val listaPaises = mutableSetOf<Pais>()

    private fun encontrarPais(pais: String)= api.buscarPaisesPorNombre(pais)

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


//este metodo tiene que cambiar su procesamiento para que le pegue a la api y traiga el pais
//private fun encontrarPais(pais: String) : Pais {
//    if(!this.listaPaises.any{it.name == pais}){
//        throw error("el pais $pais no esta en la lista") // o mejorar el texto del error como convenga
//    }else{
//        return this.listaPaises.find{it.name == pais}!!
//    }
//}

//    private fun encontrarPais(pais: String) : Pais {
//       return api.buscarPaisesPorNombre(pais) // aca ponerle el adapter
//    }
//
//esto no es necesario. Hay un sumOf que anda con Long
//
//
//paisesLimitrofes(pais : Pais ) : List<String>{
//    return pais.borders
//}
//
//paisLimitacon(pais1:Pais , pais2:Pais) :Boolean {
//    paiseslimitrofes(pais1).contains{pais2.iso3}
//}
//[15:12, 11/11/2020] +54 9 11 6107-0780: todolospaises() {
//    api.todoslospaises().map{listapaises.add(adapter(it))}
//}
//
//esto no es necesario. Hay un sumOf que anda con Long
//
//
//[15:07, 11/11/2020] +54 9 11 6107-0780: paisesLimitrofes(pais : Pais ) : List<String>{
//    return pais.borders
//}
//
//paisLimitacon(pais1:Pais , pais2:Pais) :Boolean {
//    paiseslimitrofes(pais1).contains{pais2.iso3}
//}
//[15:12, 11/11/2020] +54 9 11 6107-0780: todolospaises() {
//    api.todoslospaises().map{listapaises.add(adapter(it))}
//}
