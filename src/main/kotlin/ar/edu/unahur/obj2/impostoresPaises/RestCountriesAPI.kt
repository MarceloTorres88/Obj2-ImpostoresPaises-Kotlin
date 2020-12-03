package ar.edu.unahur.obj2.impostoresPaises

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Type

// Pueden mirar cómo está hecho si les da curiosidad,
// pero no pueden cambiar absolutamente nada de este archivo.

abstract class Apis {
    abstract val urlBase : String
    val client = OkHttpClient()
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun <T> obtenerRespuesta(ruta: String, adapter: JsonAdapter<T>): T? {
        val response = client.newCall(crearRequest(urlBase + ruta)).execute()
        return if (response.isSuccessful) { adapter.fromJson(response.body!!.source())!! } else { null }
    }

     fun crearRequest(url: String): Request {
        return Request.Builder()
            .url(url)
            .build()
    }

     fun <T> crearAdapter(type: Type): JsonAdapter<T> {
        return moshi.adapter(type)
    }
}

open class RestCountriesAPI  : Apis() {
  override val urlBase = "https://restcountries.eu/rest/v2"
//  private val client = OkHttpClient()
//  private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

  private val countriesAdapter = crearAdapter<List<Country>>(
    Types.newParameterizedType(List::class.java, Country::class.java)
  )

  private val countryAdapter = crearAdapter<Country>(Country::class.java)

  fun todosLosPaises() = obtenerRespuesta("/all", countriesAdapter)!!

  fun buscarPaisesPorNombre(nombre: String) =
    obtenerRespuesta("/name/${nombre}", countriesAdapter).orEmpty()

  fun paisConCodigo(alpha3Code: String) =
    checkNotNull(
      obtenerRespuesta("/alpha/${alpha3Code}", countryAdapter),
      { "No se encontró ningún país con el código $alpha3Code" }
    )

//  private fun <T> obtenerRespuesta(ruta: String, adapter: JsonAdapter<T>): T? {
//    val response = client.newCall(crearRequest(urlBase + ruta)).execute()
//    return if (response.isSuccessful) { adapter.fromJson(response.body!!.source())!! } else { null }
//  }
//
//  private fun crearRequest(url: String): Request {
//    return Request.Builder()
//      .url(url)
//      .build()
//  }
//
//  private fun <T> crearAdapter(type: Type): JsonAdapter<T> {
//    return moshi.adapter(type)
//  }
}

// Tomamos solamente un subconjunto de la información que da la API.
// Todos los campos disponibles pueden verse en http://restcountries.eu/#api-endpoints-response-example.

data class Country(
    val name: String,
    val alpha3Code: String,
    val alpha2Code: String,
    val capital: String,
    val region: String,
    val population: Long,
    val borders: List<String>,
    val languages: List<Language>,
    val regionalBlocs: List<RegionalBloc>
)

data class Language(
  val name: String
)

data class RegionalBloc(
  val acronym: String,
  val name: String
)

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class CovidApi : Apis() {
    override val urlBase: String = "https://api.covid19api.com/"

    private val covidAdapter = crearAdapter<CovidCountry>(CovidCountry::class.java) // este es para 1 solo

    private val covidsAdapter = crearAdapter<List<CovidCountry>>( // este es para muchos
        Types.newParameterizedType(List::class.java, CovidCountry::class.java)
    )

//    fun todosLosPaises() = obtenerRespuesta("countries", covidAdapter)!!


//    fun buscarPaisesPorNombre(nombre: String) =
//        obtenerRespuesta("/name/${nombre}", covidAdapter)//.orEmpty()

//    https://api.covid19api.com/country/south-africa/status/confirmed?from=2020-03-01T00:00:00Z&to=2020-04-01T00:00:00Z

//    fun paisConCodigo(alpha3Code: String) =
//        checkNotNull(
//            obtenerRespuesta("/alpha/${alpha3Code}", covidAdapter),
//            { "No se encontró ningún país con el código $alpha3Code" }
//        )

//    https://api.covid19api.com/country/argentina/status/confirmed?from=2020-09-08T00:00:00Z&to=2020-09-09T00:00:00Z

    fun prueba () = obtenerRespuesta("country/argentina/status/confirmed?from=2020-09-08T00:00:00Z&to=2020-09-09T00:00:00Z", covidsAdapter)

    fun buscarCasosPorPaisPorFecha(pais : String ,inicio: String ): List<CovidCountry> { //fecha en este formato : 2020-03-01T00:00:00Z (año,mes,dia)
        val fin = fechaMas1Dia(inicio)
        return obtenerRespuesta("country/${pais}/status/confirmed?from=${inicio}&to=${fin}", covidsAdapter).orEmpty()
    }

    fun fechaMas1Dia(inicio: String) = //2020-03-01T00:00:00Z (el dia es el del medio) y solo caso feliz (que no cambia el mes)
        inicio.substring(0,9)+inicio[9].plus(1)+inicio.substring(10,20)


}

data class CovidCountry(
    val Country : String,
    val CountryCode : String,
    val Cases : Int,
    val Status : String,
    val Date : String
)
