package ar.edu.unahur.obj2.impostoresPaises

import javax.naming.event.ObjectChangeListener

object Observatorio {
    val api = RestCountriesAPI() //Aca esta la api

    val listaPaises = mutableSetOf<Pais>() // esto va a desaparecer cuando implemente api

    // este metodo tiene que cambiar su procesamiento para que le pegue a la api y traiga el pais
//    private fun encontrarPais(pais: String) : Pais {
//        if(!this.listaPaises.any{it.name == pais}){
//            throw error("el pais $pais no esta en la lista") // o mejorar el texto del error como convenga
//        }else{
//            return this.listaPaises.find{it.name == pais}!!
//        }
//    }

    private fun encontrarPais(pais: String) : Pais {
       return api.buscarPaisesPorNombre(pais) // aca ponerle el adapter
    }

    fun agregarPais(pais: Pais) { // esto va a desaparecer cuando implemente api
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

        return !this.necesitanTraduccion(pais1,pais2) and aux1.comparteBloqueRegionalCon(aux2)
    }

    fun cincoPaisesConMayorPoblacion(): List<Pais> {
        val lista = this.listaPaises.sortedByDescending { it.population }
        return lista.subList(0,5)
    }

    private fun filtrarPorContinente(continente : String) =  this.listaPaises.filter { it.region == continente }

    private fun sumaPoblacionPorContinente(continente: String) = this.filtrarPorContinente(continente).sumBy { it.population }

    fun continenteMasPoblado() : String{
        val Africa = this.sumaPoblacionPorContinente("Africa")
        val Americas = this.sumaPoblacionPorContinente("Americas")
        val Asia = this.sumaPoblacionPorContinente("Asia")
        val Europe = this.sumaPoblacionPorContinente("Europe")
        val Oceania = this.sumaPoblacionPorContinente("Oceania")

        val mayorpoblacionPorContiennte = listOf<Int>(Africa,Americas,Asia,Europe,Oceania).maxOf { it }
        return when {
            mayorpoblacionPorContiennte == Africa -> "Africa"
            mayorpoblacionPorContiennte == Americas -> "Americas"
            mayorpoblacionPorContiennte == Asia -> "Asia"
            mayorpoblacionPorContiennte == Europe -> "Europe"
            else -> "Oceania"
        }
    }
}