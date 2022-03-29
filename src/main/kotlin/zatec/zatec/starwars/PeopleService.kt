package zatec.zatec.starwars

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import zatec.zatec.constants.Endpoints

@Service
class PeopleService @Autowired constructor() {

    val client: OkHttpClient = OkHttpClient()

    fun getPeople(): ApiResult<Person>? {
        val mapper = jacksonObjectMapper()

        val request = Request.Builder()
            .header("Content-Type", "application/json")
            .url(Endpoints.ENDPOINT_PEOPLE)
            .build()

        val response = client.newCall(request).execute()

        val body = response.body

        return if(response.isSuccessful && body != null){
            mapper.readValue<ApiResult<Person>>(body.string())
        } else {
            null
        }

    }

}