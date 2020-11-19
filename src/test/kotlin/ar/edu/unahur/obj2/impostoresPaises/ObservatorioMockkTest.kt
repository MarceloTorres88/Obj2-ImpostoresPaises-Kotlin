package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ObservatorioMockkTest : DescribeSpec({

    describe("tercera etapa etapa Etapa"){
        ObservatorioApi.api = mockk<RestCountriesAPI>()
        // aun asi la lista de paises mokeados se tiene que pasar por un every . lo de arriba es todo como para
        // encaminarlo , paro no se como hacerlo.
        // todavia no entiendo bien donde mockear , como y a que objeto.


        every { ObservatorioApi.api.buscarPaisesPorNombre("Argentina") } returns listOf(Country(
            "Argentina",
            "ARG",
            "Buenos Aires",
            "Americas",
            43590400,
            listOf("BOL", "BRA", "CHL", "PRY", "URY"),
            listOf(Language("Spanish"),Language("Guaraní")),
            listOf(RegionalBloc("USAN","Union of South American Nations"))
            ))

        every { ObservatorioApi.api.buscarPaisesPorNombre("Brazil") } returns listOf(Country(
            "Brazil",
            "BRA",
            "Brasília Aires",
            "Americas",
            206135893,
            listOf("ARG", "BOL", "COL", "GUF", "GUY", "PRY", "PER", "SUR", "URY", "VEN"),
            listOf(Language("Portuguese")),
            listOf(RegionalBloc("USAN","Union of South American Nations"))
        ))

        // aca devuelvo el objeto Country y no anda



        every { ObservatorioApi.encontrarPais("Bolivia (Plurinational State of)") } returns Bolivia
        every { ObservatorioApi.encontrarPais("Peru") } returns Peru
        every { ObservatorioApi.encontrarPais("Spain") } returns Spain

        // aca devuelvo el objeto Pais y tampoco

        // el tema es que el error dice :
//        java.util.NoSuchElementException: List is empty.
//        at kotlin.collections.CollectionsKt___CollectionsKt.first(_Collections.kt:212)
//        at ar.edu.unahur.obj2.impostoresPaises.ObservatorioApi.encontrarPais(ObservatorioApi.kt:16)


        describe("Primer requerimiento - Limitrofes ") {
            it("son limitrofes") {
//                every { ObservatorioApi.encontrarPais("Argentina") } returns Argentina
//                every { ObservatorioApi.encontrarPais("Brazil") } returns Brasil

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
//        describe("Los cinco paises mas poblados son"){
//            val listaResultadoOrdenada = listOf("China", "India", "United States of America", "Indonesia", "Brazil")
//            ObservatorioApi.cincoPaisesConMayorPoblacion().shouldBe(listaResultadoOrdenada)
//        }
//        describe("el continente mas poblado es Asia"){
//            ObservatorioApi.continenteMasPoblado().shouldBe("Asia")
//        }
    }
})