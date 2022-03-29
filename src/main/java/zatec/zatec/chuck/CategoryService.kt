package zatec.zatec.starwars

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import zatec.zatec.chuck.JokeSearchResult
import zatec.zatec.networking.RepositoryService
import java.io.IOException

@Service
class CategoryService @Autowired constructor() {

    val repositoryService = RepositoryService()

    fun getCategories(): List<String>? {
        val request = repositoryService.getService().getCategories().execute()

        if (!request.isSuccessful) {
            throw IOException(if (request.errorBody() == null) "Unknown error" else request.errorBody()?.string())
        }

        return request.body()
    }

    fun searchJokes(query: String): JokeSearchResult? {
        val request = repositoryService.getService().searchJokes(query).execute()

        if (!request.isSuccessful) {
            throw IOException(if (request.errorBody() == null) "Unknown error" else request.errorBody()?.string())
        }

        return request.body()

    }

}