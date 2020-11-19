package ar.edu.unahur.obj2.impostoresPaises

interface Pais {
    val name: String
    val alpha3Code: String
    val capital: String
    val region: String
    val population: Int
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
}

object Argentina : Pais{
    override val name = "Argentina"
    override val alpha3Code = "ARG"
    override val capital = "Buenos Aires"
    override val region = "Americas"
    override val population = 43590400
    override val borders = listOf("BOL", "BRA", "CHL", "PRY", "URY")
    override val languages = listOf(Language("Spanish"), Language("Guaraní")).map { it.name }
    override val regionalBlocs =  listOf(RegionalBloc("USAN","Union of South American Nations")).map { it.name }
}

object Brasil : Pais{
    override val name = "Brazil"
    override val alpha3Code = "BRA"
    override val capital = "Brasília"
    override val region = "Americas"
    override val population = 206135893
    override val borders = listOf("ARG","BOL","COL", "GUF", "GUY","PRY","PER","SUR","URY","VEN")
    override val languages = listOf(Language("Portuguese")).map { it.name }
    override val regionalBlocs =  listOf(RegionalBloc("USAN","Union of South American Nations")).map { it.name }
}

object Peru : Pais{
    override val name = "Peru"
    override val alpha3Code = "PER"
    override val capital = "Lima"
    override val region = "Americas"
    override val population = 31488700
    override val borders = listOf("BOL", "BRA", "CHL", "COL", "ECU")
    override val languages = listOf(Language("Spanish")).map { it.name }
    override val regionalBlocs =  listOf(RegionalBloc("PA","Pacific Alliance"),RegionalBloc("USAN","Union of South American Nations")).map { it.name }
}

object Bolivia : Pais{
    override val name = "Bolivia (Plurinational State of)"
    override val alpha3Code = "BOL"
    override val capital = "Sucre"
    override val region = "Americas"
    override val population = 10985059
    override val borders = listOf("ARG","BRA","CHL","PRY","PER")
    override val languages = listOf(Language("Spanish"), Language("Aymara"), Language("Quechua")).map { it.name }
    override val regionalBlocs =  listOf(RegionalBloc("USAN","Union of South American Nations")).map { it.name }
}

object Spain : Pais{
    override val name = "Spain"
    override val alpha3Code = "ESP"
    override val capital = "Real Madrid"
    override val region = "Europe"
    override val population = 46438022
    override val borders = listOf("AND","FRA","GIB","PRT","MAR")
    override val languages = listOf(Language("Spanish")).map { it.name }
    override val regionalBlocs =  listOf(RegionalBloc("EU","European Union")).map { it.name }
}

object Panama : Pais{
    override val name = "Panama"
    override val alpha3Code = "PAN"
    override val capital = "Panama City"
    override val region = "Americas"
    override val population = 3814672
    override val borders = listOf("COL","CRI")
    override val languages = listOf(Language("Spanish")).map { it.name }
    override val regionalBlocs =  listOf(RegionalBloc("CAIS","Central American Integration System")).map { it.name }
}

object China : Pais{
    override val name = "China"
    override val alpha3Code = "CHN"
    override val capital = "Beijing"
    override val region = "Asia"
    override val population = 1377422166
    override val borders = listOf("AFG", "BTN", "MMR", "HKG", "IND", "KAZ", "PRK", "KGZ", "LAO", "MAC", "MNG", "PAK", "RUS", "TJK", "VNM")
    override val languages = listOf(Language("Chinese")).map { it.name }
    override val regionalBlocs =  listOf(RegionalBloc("EU","European Union")).map { it.name }
}

class AdapterCountry(country: Country) : Pais {
    override val name: String = country.name
    override val alpha3Code: String = country.alpha3Code
    override val capital: String=country.capital
    override val region: String=country.region
    override val population: Int= country.population.toInt()
    override val borders= country.borders
    override val languages= country.languages.map { it.name }
    override val regionalBlocs= country.regionalBlocs.map { it.name }
}