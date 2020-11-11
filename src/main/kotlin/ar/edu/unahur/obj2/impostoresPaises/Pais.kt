package ar.edu.unahur.obj2.impostoresPaises

interface Pais {
    val name: String
    val alpha3Code: String
    val capital: String
    val region: String
    val population: Int
    val borders: List<String>
    val languages: List<Language>
    val regionalBlocs: List<RegionalBloc>

    fun esLimitrofeCon(pais: Pais) = this.borders.contains(pais.alpha3Code)

    fun necesitaTraductorCon(pais: Pais) : Boolean{
        val aux1 = mutableSetOf<String>()
        val aux2 = mutableSetOf<String>()

        this.languages.map{aux1.add(it.name)}
        pais.languages.map{aux2.add(it.name)}

        return aux1.intersect(aux2).isEmpty()
    }

    fun comparteBloqueRegionalCon(pais: Pais): Boolean{
        val aux1 = mutableSetOf<String>()
        val aux2 = mutableSetOf<String>()

        this.regionalBlocs.map{aux1.add(it.name)}
        pais.regionalBlocs.map{aux2.add(it.name)}

        return aux1.intersect(aux2).isNotEmpty()
    }
}

class Languages (val name : String)

class RegionalBlocs (val acronym: String, val name: String)

object Argentina : Pais{
    override val name = "Argentina"
    override val alpha3Code = "ARG"
    override val capital = "Buenos Aires"
    override val region = "Americas"
    override val population = 43590400
    override val borders = listOf<String>("BOL", "BRA", "CHL", "PRY", "URY")
    override val languages = listOf<Language>(Language("Spanish"), Language("Guaraní"))
    override val regionalBlocs =  listOf<RegionalBloc>(RegionalBloc("USAN","Union of South American Nations"))
}

object Brasil : Pais{
    override val name = "Brazil"
    override val alpha3Code = "BRA"
    override val capital = "Brasília"
    override val region = "Americas"
    override val population = 206135893
    override val borders = listOf<String>("ARG","BOL","COL", "GUF", "GUY","PRY","PER","SUR","URY","VEN")
    override val languages = listOf<Language>(Language("Portuguese"))
    override val regionalBlocs =  listOf<RegionalBloc>(RegionalBloc("USAN","Union of South American Nations"))
}

object Peru : Pais{
    override val name = "Peru"
    override val alpha3Code = "PER"
    override val capital = "Lima"
    override val region = "Americas"
    override val population = 31488700
    override val borders = listOf<String>("BOL", "BRA", "CHL", "COL", "ECU")
    override val languages = listOf<Language>(Language("Spanish"))
    override val regionalBlocs =  listOf<RegionalBloc>(RegionalBloc("PA","Pacific Alliance"),RegionalBloc("USAN","Union of South American Nations"))
}

object Bolivia : Pais{
    override val name = "Bolivia (Plurinational State of)"
    override val alpha3Code = "BOL"
    override val capital = "Sucre"
    override val region = "Americas"
    override val population = 10985059
    override val borders = listOf<String>("ARG","BRA","CHL","PRY","PER")
    override val languages = listOf<Language>(Language("Spanish"), Language("Aymara"), Language("Quechua"))
    override val regionalBlocs =  listOf<RegionalBloc>(RegionalBloc("USAN","Union of South American Nations"))
}

object España : Pais{
    override val name = "Spain"
    override val alpha3Code = "ESP"
    override val capital = "Real Madrid"
    override val region = "Europe"
    override val population = 46438022
    override val borders = listOf<String>("AND","FRA","GIB","PRT","MAR")
    override val languages = listOf<Language>(Language("Spanish"))
    override val regionalBlocs =  listOf<RegionalBloc>(RegionalBloc("EU","European Union"))
}

object Panama : Pais{
    override val name = "Panama"
    override val alpha3Code = "PAN"
    override val capital = "Panama City"
    override val region = "Americas"
    override val population = 3814672
    override val borders = listOf<String>("COL","CRI")
    override val languages = listOf<Language>(Language("Spanish"))
    override val regionalBlocs =  listOf<RegionalBloc>(RegionalBloc("CAIS","Central American Integration System"))
}

object China : Pais{
    override val name = "China"
    override val alpha3Code = "CHN"
    override val capital = "Beijing"
    override val region = "Asia"
    override val population = 1377422166
    override val borders = listOf<String>("AFG", "BTN", "MMR", "HKG", "IND", "KAZ", "PRK", "KGZ", "LAO", "MAC", "MNG", "PAK", "RUS", "TJK", "VNM")
    override val languages: List<Language> = listOf<Language>(Language("Chinese"))
    override val regionalBlocs =  listOf<RegionalBloc>(RegionalBloc("EU","European Union"))
}

class AdapterCountry(country: Country) : Pais {
    override val name: String = country.name
    override val alpha3Code: String = country.alpha3Code
    override val capital: String=country.capital
    override val region: String=country.region
    override val population: Int=country.population
    override val borders: List<String> = country.borders
    override val languages: List<Language> = country.languages
    override val regionalBlocs: List<RegionalBloc> = country.regionalBlocs
}