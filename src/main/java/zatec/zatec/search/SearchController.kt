package zatec.zatec.search

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import zatec.zatec.chuck.JokeSearchResult
import zatec.zatec.starwars.ApiResult
import zatec.zatec.starwars.CategoryService
import zatec.zatec.starwars.PeopleService
import zatec.zatec.starwars.Person

@RestController
@RequestMapping("/api")
class SearchController @Autowired constructor(val peopleService: PeopleService, val categoryService: CategoryService) {

    @RequestMapping(value = ["search"], method = [RequestMethod.GET])
    suspend fun search(@RequestParam(required = false) params: Map<String, String>): ResponseEntity<SearchResult> {
        //check for parameters
        return if(params.isNotEmpty()){

            //make two simultaneous requests
            val searchResult: SearchResult = runBlocking {
                val people = async {
                    peopleService.searchPeople(params["query"].toString())
                }
                val jokes = async {
                    categoryService.searchJokes(params["query"].toString())
                }
                SearchResult(people.await(), jokes.await())
            }

            //user is trying to search
            ResponseEntity<SearchResult>(searchResult, null, HttpStatus.OK)
        } else {
            ResponseEntity<SearchResult>(null, null, HttpStatus.BAD_REQUEST)
        }
    }

}