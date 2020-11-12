import ar.edu.unahur.obj2.impostoresPaises.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class ObservatorioApiTest : DescribeSpec({

    describe("segunda etapa Etapa"){

        ObservatorioApi.agregarPaises()

        describe("Primer requerimiento - Limitrofes ") {
            it("son limitrofes") {
                ObservatorioApi.listaPaises.size.shouldBe(250)
                ObservatorioApi.sonLimitrofes("Argentina", "Brazil").shouldBeTrue()
                ObservatorioApi.listaPaises.clear()//despues de cada it o describe hay que limpiar la lista porque sino la suma
            }
            it("son limitrofes - inverso") {
                ObservatorioApi.listaPaises.size.shouldBe(250)
                ObservatorioApi.sonLimitrofes("Brazil", "Argentina").shouldBeTrue()
                ObservatorioApi.listaPaises.clear()
            }
            it("NO son limitrofes") {
                ObservatorioApi.listaPaises.size.shouldBe(250)
                ObservatorioApi.sonLimitrofes("Argentina", "Peru").shouldBeFalse()
                ObservatorioApi.listaPaises.clear()
            }
            it("NO son limitrofes - inverso") {
                ObservatorioApi.sonLimitrofes("Peru", "Argentina").shouldBeFalse()
                ObservatorioApi.listaPaises.clear()
            }
        }

        describe("Segundo requerimiento - traduccion"){
            it("necesitan traduccion"){
                ObservatorioApi.necesitanTraduccion("Argentina","Brazil").shouldBeTrue()
                ObservatorioApi.listaPaises.clear()
            }
            it("necesitan traduccion - inverso"){
                ObservatorioApi.necesitanTraduccion("Brazil", "Argentina").shouldBeTrue()
                ObservatorioApi.listaPaises.clear()
            }
            it("NO necesitan traduccion"){
                ObservatorioApi.necesitanTraduccion("Argentina","Peru").shouldBeFalse()
                ObservatorioApi.listaPaises.clear()
            }
            it("NO necesitan traduccion - inverso"){
                ObservatorioApi.necesitanTraduccion("Peru", "Argentina").shouldBeFalse()
                ObservatorioApi.listaPaises.clear()
            }
        }

        describe("tercer requerimiento - potenciales aliados"){
            it("son aliados"){
                ObservatorioApi.sonPotencialesAliados("Argentina","Bolivia (Plurinational State of)").shouldBeTrue()
                ObservatorioApi.listaPaises.clear()
            }
            it("son aliados - inverso"){
                ObservatorioApi.sonPotencialesAliados("Bolivia (Plurinational State of)", "Argentina").shouldBeTrue()
                ObservatorioApi.listaPaises.clear()
            }
            it("no son aliados por traduccion"){
                ObservatorioApi.sonPotencialesAliados("Argentina","Brazil").shouldBeFalse()
                ObservatorioApi.listaPaises.clear()
            }
            it("no son aliados por traduccion - inverso"){
                ObservatorioApi.sonPotencialesAliados("Brazil","Argentina").shouldBeFalse()
                ObservatorioApi.listaPaises.clear()
            }
            it("no son aliados por bloque regional"){
                ObservatorioApi.sonPotencialesAliados("Argentina","Spain").shouldBeFalse()
                ObservatorioApi.listaPaises.clear()
            }
            it("no son aliados por bloque regional - inverso"){
                ObservatorioApi.sonPotencialesAliados("Spain","Argentina").shouldBeFalse()
                ObservatorioApi.listaPaises.clear()
            }
        }
        describe("Los cinco paises mas poblados son"){
            val listaResultadoOrdenada = listOf("China", "India", "United States of America", "Indonesia", "Brazil")
            ObservatorioApi.cincoPaisesConMayorPoblacion().shouldBe(listaResultadoOrdenada)
            ObservatorioApi.listaPaises.clear()
        }
        describe("el continente mas poblado es Asia"){
            ObservatorioApi.continenteMasPoblado().shouldBe("Asia")
            ObservatorioApi.listaPaises.size.shouldBe(250)
        }
    }
})
