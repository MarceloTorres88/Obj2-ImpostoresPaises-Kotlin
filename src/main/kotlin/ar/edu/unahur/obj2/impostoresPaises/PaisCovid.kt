package ar.edu.unahur.obj2.impostoresPaises

interface PaisCovid {
    val name: String
    val alpha3Code: String
    val capital: String
    val region: String
    val population: Long
    val borders: List<String>
    val languages: List<String>
    val regionalBlocs: List<String>
    val Confirmed: Int
    val Deaths: Int
    val Recovered: Int
    val Active: Int
    val Date: String

}

class AdapterCountryCovid(country: Country,covidInfo: CovidInfo) : PaisCovid {
    override val name: String = country.name
    override val alpha3Code: String = country.alpha3Code
    override val capital: String=country.capital
    override val region: String=country.region
    override val population: Long= country.population
    override val borders= country.borders
    override val languages= country.languages.map { it.name }
    override val regionalBlocs= country.regionalBlocs.map { it.name }
    override val Confirmed: Int = covidInfo.Confirmed
    override val Deaths: Int = covidInfo.Deaths
    override val Recovered: Int = covidInfo.Recovered
    override val Active: Int = covidInfo.Active
    override val Date: String = covidInfo.Date
}