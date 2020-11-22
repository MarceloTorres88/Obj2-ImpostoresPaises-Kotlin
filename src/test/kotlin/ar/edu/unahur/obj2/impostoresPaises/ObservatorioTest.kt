import ar.edu.unahur.obj2.impostoresPaises.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class ObservatorioTest : DescribeSpec({

    describe("Primera Etapa"){

        Observatorio.agregarPais(Argentina)
        Observatorio.agregarPais(Brasil)
        Observatorio.agregarPais(Peru)
        Observatorio.agregarPais(Bolivia)
        Observatorio.agregarPais(Spain)

        describe("Primer requerimiento - Limitrofes ") {
            it("son limitrofes") {
                Observatorio.sonLimitrofes("Argentina", "Brazil").shouldBeTrue()
            }
            it("son limitrofes - inverso") {
                Observatorio.sonLimitrofes("Brazil", "Argentina").shouldBeTrue()
            }
            it("NO son limitrofes") {
                Observatorio.sonLimitrofes("Argentina", "Peru").shouldBeFalse()
            }
            it("NO son limitrofes - inverso") {
                Observatorio.sonLimitrofes("Peru", "Argentina").shouldBeFalse()
            }
        }

        describe("Segundo requerimiento - traduccion"){
            it("necesitan traduccion"){
                Observatorio.necesitanTraduccion("Argentina","Brazil").shouldBeTrue()
            }
            it("necesitan traduccion - inverso"){
                Observatorio.necesitanTraduccion("Brazil", "Argentina").shouldBeTrue()
            }
            it("NO necesitan traduccion"){
                Observatorio.necesitanTraduccion("Argentina","Peru").shouldBeFalse()
            }
            it("NO necesitan traduccion - inverso"){
                Observatorio.necesitanTraduccion("Peru", "Argentina").shouldBeFalse()
            }
        }

        describe("tercer requerimiento - potenciales aliados"){
            it("son aliados"){
                Observatorio.sonPotencialesAliados("Argentina","Bolivia (Plurinational State of)").shouldBeTrue()
            }
            it("son aliados - inverso"){
                Observatorio.sonPotencialesAliados("Bolivia (Plurinational State of)", "Argentina").shouldBeTrue()
            }
            it("no son aliados por traduccion"){
                Observatorio.sonPotencialesAliados("Argentina","Brazil").shouldBeFalse()
            }
            it("no son aliados por traduccion - inverso"){
                Observatorio.sonPotencialesAliados("Brazil","Argentina").shouldBeFalse()
            }
            it("no son aliados por bloque regional"){
                Observatorio.sonPotencialesAliados("Argentina","Spain").shouldBeFalse()
            }
            it("no son aliados por bloque regional - inverso"){
                Observatorio.sonPotencialesAliados("Spain","Argentina").shouldBeFalse()
            }
        }

        describe("cuarto requerimeinto - paises con mayor poblacion"){
            it("nombres de los 5 paises con mayor poblacion"){
                // no entra panama por menor poblacion de todas
                val listaResultadoOrdenada = listOf("Brazil","Spain","Argentina","Peru","Bolivia (Plurinational State of)") // tiene que estar ordenada por poblacion
                Observatorio.cincoPaisesConMayorPoblacion().shouldBe(listaResultadoOrdenada)
            }
            it("agregamos pais con mayor poblacion y cambia lista"){
                Observatorio.agregarPais(China) //Bolivia  se va , 10 M
                val listaResultadoOrdenada = listOf("China","Brazil","Spain","Argentina","Peru") // tiene que estar ordenada por poblacion
                Observatorio.cincoPaisesConMayorPoblacion().shouldBe(listaResultadoOrdenada)
            }
        }

        describe("quinto requerimiento - continente mas poblado"){
            it("continente mas poblado asia por que tener mas habitantes"){
                Observatorio.continenteMasPoblado().shouldBe("Asia")
            }
            it("sacamos a china y cambia de continente"){
                Observatorio.listaPaises.remove(China) // sacamos a china con sus 1300 M de habitantes
                Observatorio.continenteMasPoblado().shouldBe("Americas")
            }
        }
    }
})
