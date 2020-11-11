import ar.edu.unahur.obj2.impostoresPaises.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ObservatorioApiTest : DescribeSpec({

    describe("Primera Etapa"){

        ObservatorioApi.agregarPaises()

        describe("Los cinco paises mas poblados son"){
            val listaResultadoOrdenada = listOf("China","India","United States of America","Indonesia","Brazil")
            ObservatorioApi.cincoPaisesConMayorPoblacion().shouldBe(listaResultadoOrdenada)
        }
        describe("el continente mas poblado es Asia"){
            ObservatorioApi.continenteMasPoblado().shouldBe("Asia")
        }

    }
})
