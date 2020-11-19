package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ObservatorioMockkTest : DescribeSpec({

    describe("tercera etapa etapa Etapa"){
//        ObservatorioApi.api = mockk<RestCountriesAPI>()

//        every { ObservatorioApi.encontrarPais("Argentina") } returns Argentina
//        every { ObservatorioApi.encontrarPais("Brazil") } returns Brasil
//        every { ObservatorioApi.encontrarPais("Peru") } returns Peru
//        every { ObservatorioApi.encontrarPais("Spain") } returns Spain


        describe("Primer requerimiento - Limitrofes ") {
            it("son limitrofes") {
                ObservatorioApi.sonLimitrofes("Argentina", "Brazil").shouldBeTrue()
            }
            it("son limitrofes - inverso") {
                ObservatorioApi.sonLimitrofes("Brazil", "Argentina").shouldBeTrue()
            }
            it("NO son limitrofes") {
                ObservatorioApi.sonLimitrofes("Argentina", "Peru").shouldBeFalse()
            }
            it("NO son limitrofes - inverso") {
                ObservatorioApi.sonLimitrofes("Peru", "Argentina").shouldBeFalse()
            }
        }

        describe("Segundo requerimiento - traduccion"){
            it("necesitan traduccion"){
                ObservatorioApi.necesitanTraduccion("Argentina","Brazil").shouldBeTrue()
            }
            it("necesitan traduccion - inverso"){
                ObservatorioApi.necesitanTraduccion("Brazil", "Argentina").shouldBeTrue()
            }
            it("NO necesitan traduccion"){
                ObservatorioApi.necesitanTraduccion("Argentina","Peru").shouldBeFalse()
            }
            it("NO necesitan traduccion - inverso"){
                ObservatorioApi.necesitanTraduccion("Peru", "Argentina").shouldBeFalse()
            }
        }

        describe("tercer requerimiento - potenciales aliados"){
            it("son aliados"){
                ObservatorioApi.sonPotencialesAliados("Argentina","Bolivia (Plurinational State of)").shouldBeTrue()
            }
            it("son aliados - inverso"){
                ObservatorioApi.sonPotencialesAliados("Bolivia (Plurinational State of)", "Argentina").shouldBeTrue()
            }
            it("no son aliados por traduccion"){
                ObservatorioApi.sonPotencialesAliados("Argentina","Brazil").shouldBeFalse()
            }
            it("no son aliados por traduccion - inverso"){
                ObservatorioApi.sonPotencialesAliados("Brazil","Argentina").shouldBeFalse()
            }
            it("no son aliados por bloque regional"){
                ObservatorioApi.sonPotencialesAliados("Argentina","Spain").shouldBeFalse()
            }
            it("no son aliados por bloque regional - inverso"){
                ObservatorioApi.sonPotencialesAliados("Spain","Argentina").shouldBeFalse()
            }
        }
        describe("Los cinco paises mas poblados son"){
            val listaResultadoOrdenada = listOf("China", "India", "United States of America", "Indonesia", "Brazil")
            ObservatorioApi.cincoPaisesConMayorPoblacion().shouldBe(listaResultadoOrdenada)
        }
        describe("el continente mas poblado es Asia"){
            ObservatorioApi.continenteMasPoblado().shouldBe("Asia")
        }
    }
})