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

//    fun puedeDialogarCon(pais: Pais) =  this.languages.

//    fun comparteBloqueRegionalCon(pais: Pais) = this.regionalBlocs.contains(pais.regionalBlocs)
        // Estos 2 metodos tienen que comparar elementos de 2 listas distintas para ver si alguno coincide
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