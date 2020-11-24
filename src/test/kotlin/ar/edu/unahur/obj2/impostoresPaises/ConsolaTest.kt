package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.mockk.*

class ConsolaTest: DescribeSpec ({
    describe("Consola"){
        val consola = mockk<EntradaSalida>()
        val api = mockk<RestCountriesAPI>()
        val programa = Programa(api)
        programa.entradaSalida = consola

        every { consola.escribirLinea(any()) } just Runs
        every { api.buscarPaisesPorNombre("Argentina") } returns listOf(
            Country(
                "Argentina",
                "ARG",
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
                "Brasília",
                "Americas",
                206135893,
                listOf("ARG", "BOL", "COL", "GUF", "GUY", "PRY", "PER", "SUR", "URY", "VEN"),
                listOf(Language("Portuguese")),
                listOf(RegionalBloc("USAN", "Union of South American Nations"))
            )
        )

        it("Buscar pais"){
            //la consola lee la opcion 1 .... buscar informacion de pais
            // andThem dice que luego, cuando se pida por segunda vez ingresar algo
            //se ingrese "arge" y buscar argentina
            // y luego devuelve el 0 para salir del menu o cerrar programa
            every { consola.leerLinea() } returns "1" andThen "arge" andThen "0"
            every { api.buscarPaisesPorNombre("arge") } returns listOf(
                Country(
                    "Argentina",
                    "ARG",
                    "Buenos Aires",
                    "Americas",
                    43590400,
                    listOf("BOL", "BRA", "CHL", "PRY", "URY"),
                    listOf(Language("Spanish"),Language("Guaraní")),
                    listOf(RegionalBloc("ARG","Union of South American Nations"))
                )
            )

            programa.iniciar()

            verify {
                consola.escribirLinea("Ingrese el nombre de un pais")
                consola.escribirLinea("El pais Argentina tiene 43590400 habitantes y habla [Spanish, Guaraní]")
                consola.escribirLinea("¡Gracias por usar nuestro programa!")
            }
        }

        it("Busca pero no encuentra el pais"){
            every { consola.leerLinea() } returns "1" andThen "argetune" andThen "0"

            every { api.buscarPaisesPorNombre("argetune") } throws Exception("No existe pais")

            programa.iniciar()

            verify {
                consola.escribirLinea("No existe tal pais, volvemos al menu.")
                consola.escribirLinea("¡Gracias por usar nuestro programa!")
            }
        }

        it("Son limitrofes"){
            every { consola.leerLinea() } returns "2" andThen "Argentina" andThen  "bra" andThen "0"
            every { api.buscarPaisesPorNombre("bra") } returns listOf(
                Country(
                    "Brazil",
                    "BRA",
                    "Brasília",
                    "Americas",
                    206135893,
                    listOf("ARG", "BOL", "COL", "GUF", "GUY", "PRY", "PER", "SUR", "URY", "VEN"),
                    listOf(Language("Portuguese")),
                    listOf(RegionalBloc("USAN", "Union of South American Nations"))
                )
            )

            programa.iniciar()


            verify {
                consola.escribirLinea("Ingrese el nombre de un pais")
                consola.escribirLinea("Ingrese el nombre de otro pais")
                //en el caso de nuestra opcion 2, siempre hace un enter despues de cada linea
                consola.escribirLinea("los paises Argentina y Brazil  son limitrofes."+"\n")
//                consola.escribirLinea("¡Gracias por usar nuestro programa!")
            }
        }

        it("Opcion 2 se ingreso mal un pais"){
            every { consola.leerLinea() } returns "2" andThen "argola" andThen "0"

            every { api.buscarPaisesPorNombre("argola") } throws Exception("No existe pais")

            programa.iniciar()

            verify {
                consola.escribirLinea("Ingrese el nombre de un pais")
                consola.escribirLinea("Se ingreso mal un pais, volvemos al menu.")
                consola.escribirLinea("¡Gracias por usar nuestro programa!")
            }
        }

        it("Continente mas poblado"){
            every { consola.leerLinea() } returns "6" andThen "0"
            every { api.todosLosPaises() } returns listOf(
                Country(
                    "Indonesia",
                    "IDN",
                    "Jakarta",
                    "Asia",
                    258705000,
                    listOf("TLS", "MYS", "PNG"),
                    listOf(Language("Indonesian")),
                    listOf(RegionalBloc("ASEAN","Association of Southeast Asian Nations"))
                )
            )

            programa.iniciar()

            verify {
                consola.escribirLinea("El continenete mas poblado es:")
                consola.escribirLinea("Asia")
                consola.escribirLinea("¡Gracias por usar nuestro programa!")
            }
        }

    }
})