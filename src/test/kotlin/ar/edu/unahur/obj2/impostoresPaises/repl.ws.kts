
import ar.edu.unahur.obj2.impostoresPaises.RestCountriesAPI

// Algunos ejemplos para que jueguen un poco
// con lo que devuelve la API

val api = RestCountriesAPI()

api.buscarPaisesPorNombre("brazil")

api.paisConCodigo("PER")

api.paisConCodigo("BOL")

api.buscarPaisesPorNombre("panama")

api.buscarPaisesPorNombre("spa")

api.buscarPaisesPorNombre("china")



val a = api.todosLosPaises().groupBy { it.region }.mapValues { entry -> entry.value.sumOf { it.population }}

a


val b = api.todosLosPaises().groupBy { it.region }.mapValues{ pais -> pais.value.sumOf { it.population }}.maxByOrNull { it.value }!!.key

b
