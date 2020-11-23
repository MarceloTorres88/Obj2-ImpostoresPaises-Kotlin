package ar.edu.unahur.obj2.impostoresPaises

interface Pais {
    val name: String
    val alpha3Code: String
    val capital: String
    val region: String
    val population: Long
    val borders: List<String>
    val languages: List<String>
    val regionalBlocs: List<String>

    fun esLimitrofeCon(pais: Pais) = this.borders.contains(pais.alpha3Code)

    fun necesitaTraductorCon(pais: Pais) : Boolean{
        val aux1 = mutableSetOf<String>()
        val aux2 = mutableSetOf<String>()

        aux1.addAll(this.languages)
        aux2.addAll(pais.languages)

        return aux1.intersect(aux2).isEmpty()
    }

    fun comparteBloqueRegionalCon(pais: Pais): Boolean{
        val aux1 = mutableSetOf<String>()
        val aux2 = mutableSetOf<String>()

        aux1.addAll(this.regionalBlocs)
        aux2.addAll(pais.regionalBlocs)

        return aux1.intersect(aux2).isNotEmpty()
    }

    fun sonPotencialesAliados(pais: Pais) = this.comparteBloqueRegionalCon(pais) and !this.necesitaTraductorCon(pais)
}

class AdapterCountry(country: Country) : Pais {
    override val name: String = country.name
    override val alpha3Code: String = country.alpha3Code
    override val capital: String=country.capital
    override val region: String=country.region
    override val population: Long= country.population
    override val borders= country.borders
    override val languages= country.languages.map { it.name }
    override val regionalBlocs= country.regionalBlocs.map { it.name }
}