package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.mockk.*

class ConsolaTest: DescribeSpec ({
    describe("Consola"){
        val consola = mockk<EntradaSalida>()
        Programa.entradaSalida = consola

        every { consola.escribirLinea(any()) } just Runs

        it("Buscar pais"){
            //la consola lee la opcion 1 .... buscar informacion de pais
            // andThem dice que luego, cuando se pida por segunda vez ingresar algo
            //se ingrese "arge" y buscar argentina
            // y luego devuelve el 0 para salir del menu o cerrar programa
            every { consola.leerLinea() } returns "1" andThen "arge" andThen "0"

            Programa.iniciar()

            verify {
                consola.escribirLinea("Ingrese el nombre de un pais")
                consola.escribirLinea("El pais Argentina tiene 43590400 habitantes y habla [Spanish, Guaraní]")
            }
        }

        it("Busca pero no encuentra el pais"){
            every { consola.leerLinea() } returns "1" andThen "argetune" andThen "0"

            Programa.iniciar()

            verify {
                consola.escribirLinea("No existe tal pais, volvemos al menu.")
            }
        }

        it("Son limitrofes"){
            every { consola.leerLinea() } returns "2" andThen "arge" andThen  "bra" andThen "0"

            Programa.iniciar()

            verify {
                consola.escribirLinea("Ingrese el nombre de un pais")
                consola.escribirLinea("Ingrese el nombre de otro pais")
                //en el caso de nuestra opcion 2, siempre hace un enter despues de cada linea
                consola.escribirLinea("los paises Argentina y Brazil  son limitrofes."+"\n")
            }
        }

        it("Opcion 2 se ingreso mal un pais"){
            every { consola.leerLinea() } returns "2" andThen "argola" andThen "0"

            Programa.iniciar()

            verify {
                consola.escribirLinea("Ingrese el nombre de un pais")
                consola.escribirLinea("Se ingreso mal un pais, volvemos al menu.")
            }
        }

        it("Continente mas poblado"){
            every { consola.leerLinea() } returns "6" andThen "0"

            Programa.iniciar()

            verify {
                consola.escribirLinea("El continenete mas poblado es:")
                consola.escribirLinea("Asia")
            }
        }

    }
})