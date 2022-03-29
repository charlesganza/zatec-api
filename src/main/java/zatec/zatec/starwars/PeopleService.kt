package zatec.zatec.starwars

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import zatec.zatec.networking.RepositoryService
import java.io.IOException

@Service
class PeopleService @Autowired constructor() {

    val repositoryService = RepositoryService()

    fun getPeople(): ApiResult<Person>? {
        val request = repositoryService.getService().getPeople().execute()

        if (!request.isSuccessful) {
            throw IOException(if (request.errorBody() == null) "Unknown error" else request.errorBody()?.string())
        }

        return request.body()
    }

    fun searchPeople(query: String): ApiResult<Person>? {
        val request = repositoryService.getService().searchPeople(query).execute()

        if (!request.isSuccessful) {
            throw IOException(if (request.errorBody() == null) "Unknown error" else request.errorBody()?.string())
        }

        return request.body()
    }

}