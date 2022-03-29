package zatec.zatec.search

import com.fasterxml.jackson.annotation.JsonProperty
import zatec.zatec.chuck.JokeSearchResult
import zatec.zatec.starwars.ApiResult
import zatec.zatec.starwars.Person

data class SearchResult(
    @JsonProperty("people") val people: PeopleResult?,
    @JsonProperty("jokes") val jokes: JokesResultResult?
)

data class PeopleResult(@JsonProperty("link") val link: String,
                        @JsonProperty("people") val people: ApiResult<Person>?)

data class JokesResultResult(@JsonProperty("link") val link: String,
                             @JsonProperty("jokes") val jokes: JokeSearchResult?)