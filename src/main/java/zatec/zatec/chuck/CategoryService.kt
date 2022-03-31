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
        return repositoryService.getService().getCategories().execute().body()
    }

    fun searchJokes(query: String): JokeSearchResult? {
        return repositoryService.getService().searchJokes(query).execute().body()
    }

}