import ar.edu.unahur.obj2.impostoresPaises.Pais
import ar.edu.unahur.obj2.impostoresPaises.RestCountriesAPI

// Algunos ejemplos para que jueguen un poco
// con lo que devuelve la API

val api = RestCountriesAPI()

api.buscarPaisesPorNombre("brazil")

api.buscarPaisesPorNombre("argent")

api.paisConCodigo("PER")

api.paisConCodigo("ARG")

api.paisConCodigo("BOL")

api.buscarPaisesPorNombre("panama")

api.buscarPaisesPorNombre("spa")

api.buscarPaisesPorNombre("china")

// para primer commit

val asdf = listOf<Pais>()

asdf.sumBy { it.population }.toString()
