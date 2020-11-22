import ar.edu.unahur.obj2.impostoresPaises.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class ObservatorioApiTest : DescribeSpec({

    describe("segunda etapa Etapa"){
        val api = RestCountriesAPI()
        val observatorio = ObservatorioApi(api)

        describe("Primer requerimiento - Limitrofes ") {
            it("son limitrofes") {
                observatorio.sonLimitrofes("Argentina", "Brazil").shouldBeTrue()
            }
            it("son limitrofes - inverso") {
                observatorio.sonLimitrofes("Brazil", "Argentina").shouldBeTrue()
            }
            it("NO son limitrofes") {
                observatorio.sonLimitrofes("Argentina", "Peru").shouldBeFalse()
            }
            it("NO son limitrofes - inverso") {
                observatorio.sonLimitrofes("Peru", "Argentina").shouldBeFalse()
            }
        }

        describe("Segundo requerimiento - traduccion"){
            it("necesitan traduccion"){
                observatorio.necesitanTraduccion("Argentina","Brazil").shouldBeTrue()
            }
            it("necesitan traduccion - inverso"){
                observatorio.necesitanTraduccion("Brazil", "Argentina").shouldBeTrue()
            }
            it("NO necesitan traduccion"){
                observatorio.necesitanTraduccion("Argentina","Peru").shouldBeFalse()
            }
            it("NO necesitan traduccion - inverso"){
                observatorio.necesitanTraduccion("Peru", "Argentina").shouldBeFalse()
            }
        }

        describe("tercer requerimiento - potenciales aliados"){
            it("son aliados"){
                observatorio.sonPotencialesAliados("Argentina","Bolivia (Plurinational State of)").shouldBeTrue()
            }
            it("son aliados - inverso"){
                observatorio.sonPotencialesAliados("Bolivia (Plurinational State of)", "Argentina").shouldBeTrue()
            }
            it("no son aliados por traduccion"){
                observatorio.sonPotencialesAliados("Argentina","Brazil").shouldBeFalse()
            }
            it("no son aliados por traduccion - inverso"){
                observatorio.sonPotencialesAliados("Brazil","Argentina").shouldBeFalse()
            }
            it("no son aliados por bloque regional"){
                observatorio.sonPotencialesAliados("Argentina","Spain").shouldBeFalse()
            }
            it("no son aliados por bloque regional - inverso"){
                observatorio.sonPotencialesAliados("Spain","Argentina").shouldBeFalse()
            }
        }
        describe("Los cinco paises mas poblados son"){
            val listaResultadoOrdenada = listOf("China", "India", "United States of America", "Indonesia", "Brazil")
            observatorio.cincoPaisesConMayorPoblacion().shouldBe(listaResultadoOrdenada)
        }
        describe("el continente mas poblado es Asia"){
            observatorio.continenteMasPoblado().shouldBe("Asia")
        }
    }
})
