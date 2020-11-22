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

object Argentina : Pais{
    override val name = "Argentina"
    override val alpha3Code = "ARG"
    override val capital = "Buenos Aires"
    override val region = "Americas"
    override val population: Long = 43590400
    override val borders = listOf("BOL", "BRA", "CHL", "PRY", "URY")
    override val languages = listOf("Spanish","Guaraní")
    override val regionalBlocs =  listOf("Union of South American Nations")
}

object Brasil : Pais{
    override val name = "Brazil"
    override val alpha3Code = "BRA"
    override val capital = "Brasília"
    override val region = "Americas"
    override val population: Long = 206135893
    override val borders = listOf("ARG","BOL","COL", "GUF", "GUY","PRY","PER","SUR","URY","VEN")
    override val languages = listOf("Portuguese")
    override val regionalBlocs =  listOf("Union of South American Nations")
}

object Peru : Pais{
    override val name = "Peru"
    override val alpha3Code = "PER"
    override val capital = "Lima"
    override val region = "Americas"
    override val population: Long = 31488700
    override val borders = listOf("BOL", "BRA", "CHL", "COL", "ECU")
    override val languages = listOf("Spanish")
    override val regionalBlocs =  listOf("Pacific Alliance","Union of South American Nations")
}

object Bolivia : Pais{
    override val name = "Bolivia (Plurinational State of)"
    override val alpha3Code = "BOL"
    override val capital = "Sucre"
    override val region = "Americas"
    override val population: Long = 10985059
    override val borders = listOf("ARG","BRA","CHL","PRY","PER")
    override val languages = listOf("Spanish","Aymara","Quechua")
    override val regionalBlocs =  listOf("Union of South American Nations")
}

object Spain : Pais{
    override val name = "Spain"
    override val alpha3Code = "ESP"
    override val capital = "Real Madrid"
    override val region = "Europe"
    override val population: Long = 46438022
    override val borders = listOf("AND","FRA","GIB","PRT","MAR")
    override val languages = listOf("Spanish")
    override val regionalBlocs =  listOf("European Union")
}

object China : Pais{
    override val name = "China"
    override val alpha3Code = "CHN"
    override val capital = "Beijing"
    override val region = "Asia"
    override val population: Long = 1377422166
    override val borders = listOf("AFG", "BTN", "MMR", "HKG", "IND", "KAZ", "PRK", "KGZ", "LAO", "MAC", "MNG", "PAK", "RUS", "TJK", "VNM")
    override val languages = listOf("Chinese")
    override val regionalBlocs =  listOf("European Union")
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