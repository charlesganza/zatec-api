package zatec.zatec.starwars

import com.fasterxml.jackson.annotation.JsonProperty

class Person(
    @JsonProperty("name") val name: String,
    @JsonProperty("height") val height: String,
    @JsonProperty("mass") val mass: String,
    @JsonProperty("hair_color") val hairColor: String,
    @JsonProperty("skin_color") val skinColor: String,
    @JsonProperty("eye_color") val eyeColor: String,
    @JsonProperty("birth_year") val birthYear: String,
    @JsonProperty("gender") val gender: String,
    @JsonProperty("home_world") val homeWorld: String,
    @JsonProperty("films") val films: List<String>,
    @JsonProperty("vehicles") val vehicles: List<String>,
    @JsonProperty("star_ships") val starShips: List<String>,
    @JsonProperty("created") val created: String,
    @JsonProperty("edited") val edited: String
)