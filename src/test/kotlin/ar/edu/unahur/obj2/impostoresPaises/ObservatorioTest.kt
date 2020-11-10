import io.kotest.core.spec.style.DescribeSpec

class ObservatorioTest : DescribeSpec({

    describe("Primera Etapa"){

        val argentina = Pais("argentina") // despues modificar para que coincidan con objeto creado pais
        val brasil = Pais("brasil") // despues modificar para que coincidan con objeto creado pais
        val peru = Pais("peru") // despues modificar para que coincidan con objeto creado pais
        val bolivia = Pais("bolivia") // despues modificar para que coincidan con objeto creado pais
        val españa = Pais("españa") // despues modificar para que coincidan con objeto creado pais
        val panama = Pais("panama") // despues modificar para que coincidan con objeto creado pais
        val china = Pais("china") // despues modificar para que coincidan con objeto creado pais

        Observatorio.agregarPais(argentina)
        Observatorio.agregarPais(brasil)
        Observatorio.agregarPais(peru)
        Observatorio.agregarPais(bolivia)
        Observatorio.agregarPais(españa)
        Observatorio.agregarPais(panama)

        describe("Primer requerimiento - Limitrofes ") {
            it("son limitrofes") {
                Observatorio.sonLimitrofes(argentina, brasil).shouldBeTrue()
            }
            it("son limitrofes - inverso") {
                Observatorio.sonLimitrofes(brasil, argentina).shouldBeTrue()
            }
            it("NO son limitrofes") {
                Observatorio.sonLimitrofes(argentina, peru).shouldBeFalse()
            }
            it("NO son limitrofes - inverso") {
                Observatorio.sonLimitrofes(peru, argentina).shouldBeFalse()
            }
        }

        describe("Segundo requerimiento - traduccion"){
            it("necesitan traduccion"){
                Observatorio.necesitanTraduccion(argentina,brasil).shouldBeTrue()
            }
            it("necesitan traduccion - inverso"){
                Observatorio.necesitanTraduccion(brasil, argentina).shouldBeTrue()
            }
            it("NO necesitan traduccion"){
                Observatorio.necesitanTraduccion(argentina,peru).shouldBeFalse()
            }
            it("NO necesitan traduccion - inverso"){
                Observatorio.necesitanTraduccion(peru, argentina).shouldBeFalse()
            }
        }

        describe("tercer requerimiento - potenciales aliados"){
            it("son aliados"){
                Observatorio.sonPotencialesAliados(argentina,bolivia).shouldBeTrue()
            }
            it("son aliados - inverso"){
                Observatorio.sonPotencialesAliados(bolivia, argentina).shouldBeTrue()
            }
            it("no son aliados por traduccion"){
                Observatorio.sonPotencialesAliados(argentina,brasil).shouldBeFalse()
            }
            it("no son aliados por traduccion - inverso"){
                Observatorio.sonPotencialesAliados(brasil,argentina).shouldBeFalse()
            }
            it("no son aliados por bloque regional"){
                Observatorio.sonPotencialesAliados(argentina,españa).shouldBeFalse()
            }
            it("no son aliados por bloque regional - inverso"){
                Observatorio.sonPotencialesAliados(españa,argentina).shouldBeFalse()
            }
        }

        describe("cuarto requerimeinto - paises con mayor poblacion"){
            it("nombres de los 5 paises con mayor poblacion"){
                // no entra panama por menor poblacion de todas
                Observatorio.cincoPaisesConMayorPoblacion().shouldBe(List<Pais>(argentina,brasil,peru,bolivia,españa))
            }
            it("agregamos pais con mayor poblacion y cambia lista"){
                Observatorio.agregarPais(china)
                //peru se va , 10 M
                Observatorio.cincoPaisesConMayorPoblacion().shouldBe(List<Pais>(argentina,brasil,bolivia,españa,china))
            }
        }

        describe("quinto requerimiento - continente mas poblado"){
            it("continente mas poblado america por que en la lista tiene 5 paises"){
                Observatorio.continenteMasPoblado().shouldBe("america")// ver si devuelve esto, y si no cambiarlo
            }
            it("agrego china y cambia de continente"){
                Observatorio.agregarPais(china) // agrego china y sus 1300 M de habitantes
                Observatorio.continenteMasPoblado().shouldBe("asia")// ver si devuelve esto, y si no cambiarlo
            }
        }
    }
})
