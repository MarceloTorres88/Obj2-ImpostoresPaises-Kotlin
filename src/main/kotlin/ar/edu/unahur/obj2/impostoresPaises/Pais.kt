package ar.edu.unahur.obj2.impostoresPaises

interface Pais {
    val name: String
    val alpha3Code: String
    val capital: String
    val region: String
    val population: Int
    val borders: List<String>
    val languages: List<Lenguaje>
    val regionalBlocs: List<BloqueRegional>

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

abstract class Lenguaje  {
    abstract val name: String
}

abstract class BloqueRegional {
    abstract val acronym: String
    abstract val name: String
}

object Argentina : Pais{
    override val name: String
        get() = TODO("Not yet implemented")
    override val alpha3Code: String
        get() = TODO("Not yet implemented")
    override val capital: String
        get() = TODO("Not yet implemented")
    override val region: String
        get() = TODO("Not yet implemented")
    override val population: Int
        get() = TODO("Not yet implemented")
    override val borders: List<String>
        get() = TODO("Not yet implemented")
    override val languages: List<Lenguaje>
        get() = TODO("Not yet implemented")
    override val regionalBlocs: List<BloqueRegional>
        get() = TODO("Not yet implemented")

}