package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.mockk.*

class ConsolaTest: DescribeSpec ({
    describe("Consola"){
        val consola = mockk<EntradaSalida>()
        Programa.entradaSalida = consola

        every { consola.escribirLinea(any()) } just Runs

        it("Buscar pais"){
            every { consola.leerLinea() } returns "1" andThen "arge" andThen "0"

            Programa.iniciar()

            verify {
                consola.escribirLinea("Ingrese el nombre de un pais")
                consola.escribirLinea("El pais Argentina tiene 43590400 habitantes y habla [Spanish, Guaraní]")
            }
        }

    }
})