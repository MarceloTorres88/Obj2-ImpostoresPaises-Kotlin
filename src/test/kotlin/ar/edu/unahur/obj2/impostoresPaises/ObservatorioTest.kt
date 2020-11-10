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
        Observatorio.agregarPais(España)
        Observatorio.agregarPais(Panama)

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
                Observatorio.sonPotencialesAliados("Argentina","Bolivia").shouldBeTrue()
            }
            it("son aliados - inverso"){
                Observatorio.sonPotencialesAliados("Bolivia", "Argentina").shouldBeTrue()
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
                Observatorio.cincoPaisesConMayorPoblacion().shouldBe(List<Pais>(Argentina,Brasil,Peru,Bolivia,España))
            }
            it("agregamos pais con mayor poblacion y cambia lista"){
                Observatorio.agregarPais(China)
                //peru se va , 10 M
                Observatorio.cincoPaisesConMayorPoblacion().shouldBe(List<Pais>(Argentina,Brasil,Bolivia,España,China))
            }
        }

        describe("quinto requerimiento - continente mas poblado"){
            it("continente mas poblado america por que en la lista tiene 5 paises"){
                Observatorio.continenteMasPoblado().shouldBe("Americas")// ver si devuelve esto, y si no cambiarlo
            }
            it("agrego china y cambia de continente"){
                Observatorio.agregarPais(China) // agrego china y sus 1300 M de habitantes
                Observatorio.continenteMasPoblado().shouldBe("Asia")// ver si devuelve esto, y si no cambiarlo
            }
        }
    }
})
