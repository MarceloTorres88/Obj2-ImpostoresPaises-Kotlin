
import ar.edu.unahur.obj2.impostoresPaises.CovidApi
import ar.edu.unahur.obj2.impostoresPaises.CovidMarce
import ar.edu.unahur.obj2.impostoresPaises.RestCountriesAPI
import io.mockk.InternalPlatformDsl.toStr

val coMarce = CovidMarce()
coMarce.todosLosPaises()
//val covidapi = CovidApi()

//https://api.covid19api.com/country/argentina/status/confirmed?from=2020-09-08T00:00:00Z&to=2020-09-09T00:00:00Z
//covidapi.fechaMas1Dia("2020-09-08T00:00:00Z")
//covidapi.buscarCasosPorPaisPorFecha("argentina", "2020-09-08T00:00:00Z")
//covidapi.prueba()

// Algunos ejemplos para que jueguen un poco
// con lo que devuelve la API

//val api = RestCountriesAPI()
//
//api.buscarPaisesPorNombre("russ")
//
//api.buscarPaisesPorNombre("brazil")

//api.paisConCodigo("PER")

//api.paisConCodigo("BOL")

//api.buscarPaisesPorNombre("spa")

//api.buscarPaisesPorNombre("china")
//
//api.buscarPaisesPorNombre("India")
//
//api.buscarPaisesPorNombre("united states of america")
//
//api.buscarPaisesPorNombre("nigeria")
//
//


//val a = api.todosLosPaises().groupBy { it.region }.mapValues { entry -> entry.value.sumOf { it.population }}
//
//a
//
//
//val b = api.todosLosPaises().groupBy { it.region }
//    .mapValues{ pais -> pais.value.sumOf { it.population }}
//    .maxByOrNull { it.value }!!.key
//
//b
//
//val observatorio = ObservatorioApi(api)
//val c = observatorio.listaPaises.groupBy { it.region }
//    .mapValues{ pais -> pais.value.sumOf { it.population }}
//    .maxByOrNull { it.value }!!.key
//
//c
//
//val d = observatorio.listaPaises.count()
//
//d

//api.todosLosPaises()


