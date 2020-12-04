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

abstract class Apis { // aca adapte como superclase lo que hace que se comunique con la api y lo transforme en algo que podamos usar
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

  private val countriesAdapter = crearAdapter<List<Country>>( // esto es para lista de contryes
    Types.newParameterizedType(List::class.java, Country::class.java)
  )

  private val countryAdapter = crearAdapter<Country>(Country::class.java) // esto es para un solo contry

  fun todosLosPaises() = obtenerRespuesta("/all", countriesAdapter)!!

  fun buscarPaisesPorNombre(nombre: String) =
    obtenerRespuesta("/name/${nombre}", countriesAdapter).orEmpty()

  fun paisConCodigo(alpha3Code: String) =
    checkNotNull(
      obtenerRespuesta("/alpha/${alpha3Code}", countryAdapter),
      { "No se encontró ningún país con el código $alpha3Code" }
    )
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

    private val infoCovidAdapter = crearAdapter<CovidCountry>(CovidCountry::class.java) // este es para 1 solo contry covid

    private val infoCovidVariosAdapter = crearAdapter<List<CovidCountry>>( // este es para muchos country covids
        Types.newParameterizedType(List::class.java, CovidCountry::class.java)
    )

//    https://api.covid19api.com/country/argentina/status/confirmed?from=2020-09-08T00:00:00Z&to=2020-09-09T00:00:00Z
    // para probar en navegador el enlace que quiero que devuelva prueba()

    fun prueba () = obtenerRespuesta("country/argentina/status/confirmed?from=2020-09-08T00:00:00Z&to=2020-09-09T00:00:00Z", infoCovidVariosAdapter)
    // prueba de que realmente entrega lo que quiero

    fun buscarCasosPorPaisPorFecha(pais : String ,inicio: String ): List<CovidCountry> { //fecha en este formato : 2020-03-01T00:00:00Z (año,mes,dia)
        val fin = fechaMas1Dia(inicio)
        return obtenerRespuesta("country/${pais}/status/confirmed?from=${inicio}&to=${fin}", infoCovidVariosAdapter).orEmpty()
    }

    fun fechaMas1Dia(inicio: String) = //2020-03-01T00:00:00Z y solo caso feliz (que no cambia el mes no el primer digito del dia)
        inicio.substring(0,9)+inicio[9].plus(1)+inicio.substring(10,20)
        // corto el string varias veces y en la posicion del dia (segundo digito) le sumo 1


}

data class CovidCountry(
    val Country : String,
    val CountryCode : String,
    val Cases : Int,
    val Status : String,
    val Date : String
)

class CovidMarce : Apis() {
    override val urlBase: String = "https://api.covid19api.com/"

    private val infoCovidAdapter = crearAdapter<Summary>(Summary::class.java) // este es para 1 solo contry covid
    //private val infoAdapter = crearAdapter<CovidInfo>(CovidInfo::class.java)

    private val infoCovidVariosAdapter = crearAdapter<List<CovidInfo>>( // este es para muchos country covids
        Types.newParameterizedType(List::class.java, CovidInfo::class.java)
    )
    fun todosLosPaises () = obtenerRespuesta("summary", infoCovidAdapter)?.Countries//lista de todos los paises

    fun buscarPaisesPorNombre(Country: String) =
        obtenerRespuesta("live/country/${Country}", infoCovidVariosAdapter)!!.last()//busca el ultimo pais, la ultima actualizacion

}
data class Summary(
    val Countries: List<Covid>
)
data class Covid(
    val CountryCode: String,
    val NewConfirmed: Int,
    val TotalConfirmed:Int,
    val NewDeaths:Int,
    val TotalDeaths:Int,
    val NewRecovered:Int,
    val TotalRecovered:Int
)
data class CovidInfo(
    val Confirmed: Int,
    val Deaths: Int,
    val Recovered: Int,
    val Active: Int,
    val Date: String
)


