package zatec.zatec.chuck

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class JokeSearchResult(
    @JsonProperty("total") val total: Int,
    @JsonProperty("count") val result: List<Joke>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Joke(
    @JsonProperty("created_at") val total: Int,
    @JsonProperty("icon_url") val icon: String,
    @JsonProperty("id") val id: String,
    @JsonProperty("updated_at") val updatedAt: String,
    @JsonProperty("url") val url: String,
    @JsonProperty("value") val value: String
)
