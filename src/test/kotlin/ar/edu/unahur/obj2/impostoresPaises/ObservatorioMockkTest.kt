package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk


class ObservatorioMockkTest : DescribeSpec({
    val api = mockk<RestCountriesAPI>()
    val observatorio = ObservatorioApi(api)

    every { api.buscarPaisesPorNombre("Argentina") } returns listOf(
        Country(
            "Argentina",
            "ARG",
            "AR",
            "Buenos Aires",
            "Americas",
            43590400,
            listOf("BOL", "BRA", "CHL", "PRY", "URY"),
            listOf(Language("Spanish"),Language("Guaraní")),
            listOf(RegionalBloc("ARG","Union of South American Nations"))
        )
    )

    every { api.buscarPaisesPorNombre("Brazil") } returns listOf(
        Country(
            "Brazil",
            "BRA",
            "BR",
            "Brasília",
            "Americas",
            206135893,
            listOf("ARG", "BOL", "COL", "GUF", "GUY", "PRY", "PER", "SUR", "URY", "VEN"),
            listOf(Language("Portuguese")),
            listOf(RegionalBloc("USAN", "Union of South American Nations"))
        )
    )

    every { api.buscarPaisesPorNombre("Peru") } returns listOf(
        Country(
            "Peru",
            "PER",
            "PE",
            "Lima",
            "Americas",
            31488700,
            listOf("BOL", "BRA", "CHL", "COL", "ECU"),
            listOf(Language("Spanish")),
            listOf(RegionalBloc("PA","Pacific Alliance"),(RegionalBloc("USAN","Union of South American Nations")))
        )
    )

    every { api.buscarPaisesPorNombre("Bolivia (Plurinational State of)") } returns listOf(
        Country(
            "Bolivia (Plurinational State of)",
            "BOL",
            "BO",
            "Sucre",
            "Americas",
            10985059,
            listOf("ARG", "BRA", "CHL", "PRY", "PER"),
            listOf(Language("Spanish"), Language("Aymara"), Language("Quechua")),
            listOf(RegionalBloc("USAN","Union of South American Nations"))

        )
    )

    every { api.buscarPaisesPorNombre("Spain") } returns listOf(
        Country(
            "Spain",
            "ESP",
            "ES",
            "Madrid",
            "Europe",
            46438422,
            listOf("AND", "FRA", "GIB", "PRT", "MAR"),
            listOf(Language("Spanish")),
            listOf(RegionalBloc("EU", "European Union"))

        )
    )

    describe("tercera etapa etapa Etapa"){

        describe("Primer requerimiento - Limitrofes ") {

            observatorio.encontrarPais("Argentina")
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

        every { api.todosLosPaises() } returns listOf(
            Country(
                "Argentina",
                "ARG",
                "AR",
                "Buenos Aires",
                "Americas",
                43590400,
                listOf("BOL", "BRA", "CHL", "PRY", "URY"),
                listOf(Language("Spanish"),Language("Guaraní")),
                listOf(RegionalBloc("USAN","Union of South American Nations"))
            ),
            Country(
                "Brazil",
                "BRA",
                "BR",
                "Brasília",
                "Americas",
                206135893,
                listOf("ARG","BOL","COL", "GUF", "GUY","PRY","PER","SUR","URY","VEN"),
                listOf(Language("Portuguese")),
                listOf(RegionalBloc("USAN","Union of South American Nations"))
            )
            ,
            Country(
                "Peru",
                "PER",
                "PE",
                "Lima",
                "Americas",
                31488700,
                listOf("BOL", "BRA", "CHL", "COL", "ECU"),
                listOf(Language("Spanish")),
                listOf(RegionalBloc("PA","Pacific Alliance"),RegionalBloc("USAN","Union of South American Nations"))
            ),
            Country(
                "Spain",
                "ESP",
                "ES",
                "Real Madrid",
                "Europe",
                46438022,
                listOf("AND","FRA","GIB","PRT","MAR"),
                listOf(Language("Spanish")),
                listOf(RegionalBloc("EU","European Union"))
            ),
            Country(
                "Bolivia (Plurinational State of)",
                "BOL",
                "BO",
                "Sucre",
                "Americas",
                10985059,
                listOf("ARG","BRA","CHL","PRY","PER"),
                listOf(Language("Spanish"), Language("Aymara"), Language("Quechua")),
                listOf(RegionalBloc("USAN","Union of South American Nations"))
            ),
            Country(
                "China",
                "CHN",
                "CH",
                "Beijing",
                "Asia",
                1377422166,
                listOf("AFG", "BTN", "MMR", "HKG", "IND", "KAZ", "PRK", "KGZ", "LAO", "MAC", "MNG", "PAK", "RUS", "TJK", "VNM"),
                listOf(Language("Chinese")),
                listOf()
            ),
            Country(
                "India",
                "IND",
                "IN",
                "New Delhi",
                "Asia",
                1295210000,
                listOf("AFG", "BGD", "BTN", "MMR", "CHN", "NPL", "PAK", "LKA"),
                listOf(Language("Hindi"), Language("English")),
                listOf(RegionalBloc("SAARC","South Asian Association for Regional Cooperation"))
            ),
            Country(
                "United States of America",
                "USA",
                "US",
                "Washington, D.C.",
                "Americas",
                323947000,
                listOf("CAN", "MEX"),
                listOf(Language("English")),
                listOf(RegionalBloc("NAFTA","North American Free Trade Agreement"))
            ),
            Country(
                "Indonesia",
                "IDN",
                "ID",
                "Jakarta",
                "Asia",
                258705000,
                listOf("TLS", "MYS", "PNG"),
                listOf(Language("Indonesian")),
                listOf(RegionalBloc("ASEAN","Association of Southeast Asian Nations"))
            )
        )
        describe("Los cinco paises mas poblados son"){
            val listaResultadoOrdenada = listOf("China", "India", "United States of America", "Indonesia", "Brazil")
            observatorio.cincoPaisesConMayorPoblacion().shouldBe(listaResultadoOrdenada)

        }
        describe("el continente mas poblado es Asia"){
            observatorio.continenteMasPoblado().shouldBe("Asia")
        }
    }
})