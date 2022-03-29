package zatec.zatec.search

import com.fasterxml.jackson.annotation.JsonProperty
import zatec.zatec.chuck.JokeSearchResult
import zatec.zatec.starwars.ApiResult
import zatec.zatec.starwars.Person

data class SearchResult(
    @JsonProperty("people") val people: ApiResult<Person>?,
    @JsonProperty("jokes") val jokes: JokeSearchResult?
)