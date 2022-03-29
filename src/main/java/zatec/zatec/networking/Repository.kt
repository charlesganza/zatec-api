package zatec.zatec.networking

import retrofit2.Call
import retrofit2.http.*
import zatec.zatec.chuck.JokeSearchResult
import zatec.zatec.starwars.ApiResult
import zatec.zatec.starwars.Person

interface Repository {
    @GET(Endpoints.ENDPOINT_CHUCK_NORRIS)
    @Headers(ContentType.CONTENT_TYPE, ContentType.ACCEPT)
    fun getCategories(): Call<List<String>>

    @GET(Endpoints.ENDPOINT_PEOPLE)
    @Headers(ContentType.CONTENT_TYPE, ContentType.ACCEPT)
    fun getPeople(): Call<ApiResult<Person>>

    @GET(Endpoints.ENDPOINT_SEARCH_JOKES)
    @Headers(ContentType.CONTENT_TYPE, ContentType.ACCEPT)
    fun searchJokes(@Query("query") query: String): Call<JokeSearchResult>

    @GET(Endpoints.ENDPOINT_PEOPLE)
    @Headers(ContentType.CONTENT_TYPE, ContentType.ACCEPT)
    fun searchPeople(@Query("search") query: String): Call<ApiResult<Person>>
}

object ContentType {
    const val CONTENT_TYPE = "Content-Type: application/json"
    const val ACCEPT = "Accept: application/json"
}

object Endpoints {
    const val ENDPOINT_SEARCH_JOKES = "https://api.chucknorris.io/jokes/search"
    const val ENDPOINT_CHUCK_NORRIS = "https://api.chucknorris.io/jokes/categories"
    const val ENDPOINT_PEOPLE = "https://swapi.dev/api/people"
}