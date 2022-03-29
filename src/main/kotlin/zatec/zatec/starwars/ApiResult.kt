package zatec.zatec.starwars

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * response wrapper for paginated response
 * @param results [T] generic value to wrap any future objects
 * */
class ApiResult <T>(@JsonProperty("count") val count: Int,
                    @JsonProperty("results") val results: List<T>)